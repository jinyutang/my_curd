package com.hxkj.common.config;

import com.hxkj.common.constant.Constant;
import com.hxkj.common.util.ws.controller.WebSocketController;
import com.jfinal.config.Routes;

/**
 * websocket 模块路由
 * @author chuang
 * @date 2018/9/10
 */
public class WsRoute extends Routes {
    @Override
    public void config() {
        // chat 入口
        add("/ws", WebSocketController.class, Constant.VIEW_PATH);
    }
}
