package com.hxkj.common.controller;


import com.hxkj.auth.model.AuthUser;
import com.hxkj.common.interceptor.PermissionInterceptor;
import com.hxkj.sys.model.SysNotification;
import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 *  后台公共消息通知
 * @author  chuang
 */

@Clear(PermissionInterceptor.class)
public class NotificationController  extends  BaseController{


    /**
     * 通知数据
     */
    public void  notificationData(){
        AuthUser authUser = getSessionUser();
        Long userId = authUser.getId();

        int page = getParaToInt("page",1);
        int size = getParaToInt("size",10);
        String cate1 = getPara("cate1","SYSTEM");  //  USER（用户消息）  或者  SYSTEM （系统通知）
        String where = " b.receiver = "+userId+" and a.cate1 = '"+cate1+"' ";

        Page<Record> recordPage = SysNotification.dao.findNotificationWithDetailPage(page,size,where);

        // 数据处理


        renderDatagrid(recordPage);
    }


    /**
     * 消息通知详情
     */
    public void notificationDetail(){

    }

}
