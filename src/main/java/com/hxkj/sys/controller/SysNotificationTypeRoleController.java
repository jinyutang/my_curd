package com.hxkj.sys.controller;

import com.hxkj.common.constant.Constant;
import com.hxkj.common.controller.BaseController;
import com.hxkj.common.util.search.SearchSql;
import com.hxkj.sys.model.SysNotificationTypeRole;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;

/**
 * sys_notification_type_role 控制器
 *
 * @author
 * @date 2018-09-17 19:18:07
 */
public class SysNotificationTypeRoleController extends BaseController {

    /**
     * 列表页
     */
    public void index() {
        render("sys/sysNotificationTypeRole.html");
    }


    /**
     * 通知类型 角色 datagrid 数据
     */
    @Before(SearchSql.class)
    public void query() {
        int pageNumber = getAttr("pageNumber");
        int pageSize = getAttr("pageSize");
        String where = getAttr(Constant.SEARCH_SQL);
        Page<SysNotificationTypeRole> sysNotificationTypeRolePage = SysNotificationTypeRole.dao.page(pageNumber, pageSize, where);
        renderDatagrid(sysNotificationTypeRolePage);
    }


    /**
     * 增加 关联关系
     */
    @Before(Tx.class)
    public void addAction() {
        String roleIds = getPara("roleIds");
        String sysNotificationTypeId = getPara("sysNotificationTypeId");
        for (String roleId : roleIds.split(",")) {
            SysNotificationTypeRole sysNotificationTypeRole = new SysNotificationTypeRole();
            sysNotificationTypeRole.setNotificationTypeId(sysNotificationTypeId);
            sysNotificationTypeRole.setRoleId(Long.parseLong(roleId));
            SysNotificationTypeRole selectCheck = SysNotificationTypeRole.dao.findById(sysNotificationTypeId, roleId);
            if (selectCheck == null) {
                sysNotificationTypeRole.save();
            }
        }
        renderText("关联角色成功");
    }

    /**
     * 单条 删除  关联关系
     */
    @Before(Tx.class)
    public void deleteAction() {
        String sysNotificationTypeId = getPara("sysNotificationTypeId");
        String roleId = getPara("roleId");

        if (SysNotificationTypeRole.dao.deleteById(sysNotificationTypeId, roleId)) {
            renderText(Constant.DELETE_SUCCESS);
        } else {
            renderText(Constant.DELETE_FAIL);
        }
    }
}
