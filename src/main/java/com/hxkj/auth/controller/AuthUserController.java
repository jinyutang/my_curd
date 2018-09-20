package com.hxkj.auth.controller;

import com.google.common.base.Joiner;
import com.hxkj.auth.model.AuthRole;
import com.hxkj.auth.model.AuthUser;
import com.hxkj.auth.model.AuthUserRole;
import com.hxkj.common.constant.Constant;
import com.hxkj.common.controller.BaseController;
import com.hxkj.common.util.search.SearchSql;
import com.jfinal.aop.Before;
import com.jfinal.kit.HashKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;

import java.util.*;

/**
 * 系统用户
 */
public class AuthUserController extends BaseController {

    /**
     * 列表页
     */
    public void index() {
        render("auth/authUser.html");
    }


    /**
     * 列表页数据
     */
    @Before(SearchSql.class)
    public void query() {
        int pageNumber = getAttr("pageNumber");
        int pageSize = getAttr("pageSize");
        String sort = getPara("sort");
        String order = getPara("order");
        String where = getAttr(Constant.SEARCH_SQL);
        Page<AuthUser> authUsers = AuthUser.dao.page(pageNumber, pageSize, sort, order, where);
        renderDatagrid(authUsers);
    }

    /**
     * 新增 或者 编辑窗口
     */
    public void newModel() {
        Long id = getParaToLong("id");
        if (id != null) {
            AuthUser authUser = AuthUser.dao.findById(id);
            setAttr("authUser", authUser);
        }
        render("auth/authUser_form.html");
    }

    /**
     * 新增
     */
    public void addAction() {
        AuthUser authUser = getBean(AuthUser.class, "");
        String password = HashKit.sha1(authUser.getPassword());
        authUser.setPassword(password);
        authUser.setCreateTime(new Date());

        String gender = authUser.getGender();
        if("1".equals("gender")){
           authUser.setAvatar("res/image/maleAvatar.jpg");
        }else{
            authUser.setAvatar("res/image/femaleAvatar.jpg");
        }

        boolean saveFlag = authUser.save();
        if (saveFlag) {
            renderText(Constant.ADD_SUCCESS);
        } else {
            renderText(Constant.ADD_FAIL);
        }
    }

    /**
     * 修改
     */
    public void updateAction() {
        AuthUser authUser = getBean(AuthUser.class, "");

        String password = authUser.getPassword();
        // 认为密码改变
        if (password.length() < 20) {
            // 加密后为40字符
            password = HashKit.sha1(password);
            authUser.setPassword(password);
        }
        authUser.setLastEditTime(new Date());

        String gender = authUser.getGender();
        if("1".equals("gender")){
            authUser.setGender("res/image/maleAvatar.jpg");
        }else{
            authUser.setGender("res/image/femaleAvatar.jpg");
        }

        boolean updateFlag = authUser.update();
        if (updateFlag) {
            renderText(Constant.UPDATE_SUCCESS);
        } else {
            renderText(Constant.UPDATE_FAIL);
        }
    }

    /**
     * 删除
     */
    @Before(Tx.class)
    public void deleteAction() {
        Long id = getParaToLong("id");
        // 删除用户表数据
        String deleteSql = "delete from auth_user where id = ?";
        Db.update(deleteSql, id);
        // 删除用户角色中间表数据
        deleteSql = "delete from auth_user_role where user_id = ?";
        Db.update(deleteSql, id);

        renderText(Constant.DELETE_SUCCESS);
    }


    /**
     * 打开赋予角色 的窗口
     */
    public void openGiveRoleModel(){
        setAttr("userId",getPara("id"));
        render("auth/authUser_role.html");
    }

    /**
     * 用户赋予角色
     */
    @Before(Tx.class)
    public void giveRole() {
        Long userId = getParaToLong("userId");
        String roleIdstr = getPara("roleIds");
        // 删除 用户原有角色
        String deleteSql = "delete from  auth_user_role where user_id = ?";
        Db.update(deleteSql, userId);

        AuthUser authUser = getSessionUser();
        // 添加新的角色
        String[] roleIds = roleIdstr.split(";");
        for (int i = 0; i < roleIds.length; i++) {
            AuthUserRole authUserRole = new AuthUserRole();
            authUserRole.setUserId(userId);
            authUserRole.setRoleId(Long.parseLong(roleIds[i]));
            authUserRole.setUser(authUser.getId());
            authUserRole.save();
        }

        // 操作日志，非业务
        AuthUser tUser = AuthUser.dao.findById(userId);
        List<AuthRole> authRoles =
                AuthRole.dao.find("select * from auth_role where id in ("+roleIdstr.replaceAll(";",",")+")");
        List<String> roleNames = new ArrayList<>();
        for(AuthRole authRole: authRoles){
            roleNames.add(authRole.getRoleName());
        }
        String roleNamesStr = Joiner.on(",").join(roleNames);
        addOpLog("用户: "+tUser.getUsername()+", 关联角色: "+roleNamesStr);

        renderText("赋予角色成功");
    }


    /**
     * 全部角色列表数据，并根据角色选中
     */
    public void roleListChecked() {
        Long id = getParaToLong("userId");
        List<AuthUserRole> authUserRoles = AuthUserRole.dao.findUserRolesByUserId(id);
        List<AuthRole> authRoles = AuthRole.dao.findAll();
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        for (AuthRole authRole : authRoles) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", authRole.getId());
            map.put("text", authRole.getRoleName()+" ------ ["+authRole.getRoleDesc()+"]");
            map.put("state", "open");
            map.put("iconCls", "icon-blank");  // 不显示图标
            for (AuthUserRole authUserRole : authUserRoles) {
                if (authUserRole.getRoleId() == authRole.getId()) {
                    map.put("checked", true);
                    break;
                }
            }
            maps.add(map);
        }
        renderJson(maps);
    }
}
