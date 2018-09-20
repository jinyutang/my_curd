/**
 * 
 */
package com.hxkj.common.config;

import com.hxkj.common.constant.Constant;
import com.hxkj.jin.controller.JinTb2Controller;
import com.hxkj.jin.controller.JinTbController;
import com.jfinal.config.Routes;

/**
 * @author yutang.jin@finisar.com
 *
 */
public class JinRoute extends Routes {

    @Override
    public void config() {
        add("/jinTb", JinTbController.class, Constant.VIEW_PATH);
        add("/jinTb2", JinTb2Controller.class, Constant.VIEW_PATH);
    }


}
