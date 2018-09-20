package com.hxkj.common.config;


import com.hxkj.common.constant.Constant;
import com.hxkj.sys.controller.*;
import com.jfinal.config.Routes;


/**
 * 系统运行开发相关的模块路由
 *
 * @author zhangchuang
 */
public class SysRoute extends Routes {

    @Override
    public void config() {
        // 系统日志
        add("/sysOplog", SysOplogController.class, Constant.VIEW_PATH);
        // 代码生成器
        add("/sysGenerator", SysGeneratorController.class, Constant.VIEW_PATH);

        // 消息通知类型
        add("/sysNotificationType", SysNotificationTypeController.class, Constant.VIEW_PATH);
        // 系统消息通知类型 和 角色 关联
        add("/sysNotificationTypeRole", SysNotificationTypeRoleController.class,Constant.VIEW_PATH);
        // 系统消息通知类型 和 用户 关联
        add("/sysNotificationTypeUser", SysNotificationTypeUserController.class,Constant.VIEW_PATH);
    }

}
