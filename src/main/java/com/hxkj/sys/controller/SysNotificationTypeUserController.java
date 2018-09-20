package com.hxkj.sys.controller;

import com.hxkj.common.constant.Constant;
import com.hxkj.common.controller.BaseController;
import com.hxkj.common.util.search.SearchSql;
import com.hxkj.sys.model.SysNotificationTypeUser;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;

/**
 * sys_notification_type_user 控制器
 *
 * @author
 * @date 2018-09-17 19:18:07
 */
public class SysNotificationTypeUserController extends BaseController {

    /**
     * 列表页
     */
    public void index() {
        render("sys/sysNotificationTypeUser.html");
    }


    /**
     * 通知类型 用户 datagrid 数据
     */
    @Before(SearchSql.class)
    public void query() {
        int pageNumber = getAttr("pageNumber");
        int pageSize = getAttr("pageSize");
        String where = getAttr(Constant.SEARCH_SQL);
        Page<SysNotificationTypeUser> sysNotificationTypeUserPage = SysNotificationTypeUser.dao.page(pageNumber, pageSize, where);
        renderDatagrid(sysNotificationTypeUserPage);
    }



    /**
     * 增加 关联关系
     */
    public void addAction() {
        String userIds = getPara("userIds");
        String sysNotificationTypeId = getPara("sysNotificationTypeId");
        for (String userId : userIds.split(",")) {
            SysNotificationTypeUser sysNotificationTypeUser = new SysNotificationTypeUser();
            sysNotificationTypeUser.setNotificationTypeId(sysNotificationTypeId);
            sysNotificationTypeUser.setUserId(Long.parseLong(userId));
            SysNotificationTypeUser selectCheck = SysNotificationTypeUser.dao.findById(sysNotificationTypeId, userId);
            if (selectCheck == null) {
                sysNotificationTypeUser.save();
            }
        }
        renderText("关联用户成功");
    }

    /**
     * 单条 删除  关联关系
     */
    @Before(Tx.class)
    public void deleteAction() {
        String sysNotificationTypeId = getPara("sysNotificationTypeId");
        String userId = getPara("userId");
        if (SysNotificationTypeUser.dao.deleteById(sysNotificationTypeId, userId)) {
            renderText(Constant.DELETE_SUCCESS);
        } else {
            renderText(Constant.DELETE_FAIL);
        }
    }
}
