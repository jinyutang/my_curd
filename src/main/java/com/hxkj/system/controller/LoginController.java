package com.hxkj.system.controller;


import java.util.List;

import org.apache.log4j.Logger;
import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.kit.HashKit;
import com.jfinal.kit.StrKit;

import com.hxkj.common.constant.Constant;
import com.hxkj.common.interceptor.AuthorityInterceptor;
import com.hxkj.system.model.SysMenu;
import com.hxkj.system.model.SysUser;
import com.hxkj.system.model.SysUserRole;
import com.hxkj.common.util.BaseController;

public class LoginController extends BaseController {

    private final static Logger LOG = Logger.getLogger(LoginController.class);

    /**
     * 登录页面
     */
    @Clear(AuthorityInterceptor.class)
    public void index() {
        render("system/login.html");
    }

    /**
     * 登录Action
     */
    @Clear(AuthorityInterceptor.class)
    public void action() {

        String username = getPara("username");
        String password = getPara("password");

        if (StrKit.isBlank(username)) {
            setAttr("errMsg", "请填写用户名。");
            render("system/login.html");
            return;
        }

        if (StrKit.isBlank(password)) {
            setAttr("errMsg", "请填写密码。");
            render("system/login.html");
            return;
        }

        password = HashKit.sha1(password);

        String sql = "SELECT"
                + " su.*,"
                + " so.org_name AS orgName, "
                + " so.id as orgId "
                + " FROM sys_user su"
                + " LEFT JOIN sys_org so ON su.org_id = so.id"
                + " where username = ? and password = ? ";

        List<SysUser> sysUsers = SysUser.dao.find(sql, username, password);
        if (sysUsers.size() == 0) {
            setAttr("errMsg", "用户名和密码不匹配！");
            render("system/login.html");
            return;
        }

        if ( sysUsers.get(0).getDisabled().equals("1")){
            setAttr("errMsg", "该用户被禁用，请联系管理员处理。");
            render("system/login.html");
            return;
        }


        //登录用户信息
        SysUser sysUser = sysUsers.get(0);
        setSessionAttr(Constant.SYSTEM_USER, sysUser);
        //为了 druid session 监控
        setSessionAttr(Constant.SYSTEM_USER_NAME, sysUser.getName());

        // 查询角色
        String roleSql = "SELECT"
                + " GROUP_CONCAT(sur.role_id) AS roleIds,"
                + " GROUP_CONCAT(sr.role_name) AS roleNames"
                + " FROM sys_user_role sur"
                + " LEFT JOIN sys_role sr ON sur.role_id = sr.id"
                + " WHERE user_id = ? "
                + " GROUP BY sur.user_id";
        SysUserRole sysUserRole = SysUserRole.dao.findFirst(roleSql, sysUser.getId());

        if(sysUserRole!=null){
            // 角色名称
            setSessionAttr(Constant.SYSTEM_USER_ROLES, sysUserRole.get("roleNames"));
            // 查询权限
            String ownMenuSql = "SELECT"
                    + " DISTINCT sm.*"
                    + " FROM sys_role_menu srm"
                    + " LEFT JOIN sys_menu sm ON srm.menu_id = sm.id"
                    + " WHERE role_id IN (?)"
                    + " order by sm.sort";
            List<SysMenu> ownSysMenus = SysMenu.dao.find(ownMenuSql, sysUserRole.getStr("roleIds"));
            setSessionAttr(Constant.OWN_MENU, ownSysMenus);
        }else{
            setSessionAttr(Constant.SYSTEM_USER_ROLES, null);
            setSessionAttr(Constant.OWN_MENU, null);
        }


        redirect("/main");
    }


    /**
     * 退出
     */
    @Clear(AuthorityInterceptor.class)
    @ActionKey("/logout")
    public void logout() {
        removeSessionAttr(Constant.SYSTEM_USER);
        removeSessionAttr(Constant.SYSTEM_USER_ROLES);
        removeSessionAttr(Constant.OWN_MENU);
        redirect("/login");
    }



}