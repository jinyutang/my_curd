package com.hxkj.common.config;

import com.hxkj.common.constant.Constant;
import com.hxkj.common.controller.UserInfoController;
import com.hxkj.common.controller.UserPasswordController;
import com.hxkj.common.controller.UtilsController;
import com.jfinal.config.Routes;

/**
 * 公共的，不需要权限控制的 模块路由
 *
 * @author chuang
 * @date 2018-06-20 18:56:48
 */
public class CommonRoute extends Routes {
    @Override
    public void config() {
        add("/userPassword", UserPasswordController.class, Constant.VIEW_PATH);
        add("/userInfo", UserInfoController.class, Constant.VIEW_PATH);
        // 工具页面
        add("/utils", UtilsController.class,Constant.VIEW_PATH);
    }
}
