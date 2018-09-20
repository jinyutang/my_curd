package com.hxkj.sys.service;

import com.hxkj.common.util.DateTimeUtils;
import com.hxkj.common.util.ExecutorServiceUtils;
import com.hxkj.common.util.FreemarkerUtils;
import com.hxkj.common.util.Identities;
import com.hxkj.sys.model.SysNotification;
import com.hxkj.sys.model.SysNotificationDetail;
import com.hxkj.sys.model.SysNotificationType;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;

import java.util.*;

public class SysNotificationService {


    /**
     * 获得消息接收人列表
     * @return
     */
    private   Set<Long> getReceivers(SysNotificationType sysNotificationType){
        Set<Long> userIdSet = new HashSet<>();

        // 通知类型 角色表 确定用户
        List<Record> userIds = Db.find(" SELECT d.user_id " +
                "FROM " +
                "( SELECT a.role_id FROM sys_notification_type_role a, auth_role b " +
                " WHERE a.role_id = b.id  and a.notification_type_id = ? ) aa " +
                "LEFT JOIN auth_user_role d ON aa.role_id = d.role_id",sysNotificationType.getId());
        for(Record record:userIds){
            userIdSet.add(record.get("user_id"));
        }
        // 通知类型 用户表 确定用户
        userIds = Db.find("select user_id from sys_notification_type_user where notification_type_id = ? ",sysNotificationType.getId());
        for(Record record:userIds){
            userIdSet.add(record.get("user_id"));
        }

        return userIdSet;
}


    /**
     * 向数据库中添加 系统通知
     * @param sysNotification 通知，中 cate2 必须存在，为 通知类型编码
     * @param tempalteParams  模板参数
     * @return
     */
    @Before(Tx.class)
    public boolean  saveSystemNotificationData(SysNotification sysNotification, Map<String,Object> tempalteParams){
        if(sysNotification==null || sysNotification.getCate2()==null ){
            return  false;
        }
        SysNotificationType sysNotificationType = SysNotificationType.dao.findByCode(sysNotification.getCate2());
        if(sysNotificationType==null){
            return false;
        }
        Set<Long> receivers = getReceivers(sysNotificationType);
        if(receivers.size()==0){
            return false;
        }

        sysNotification.setCate1("SYSTEM");
        sysNotification.setTitle(sysNotificationType.getTxt());
        sysNotification.setContent(FreemarkerUtils.renderAsText(sysNotificationType.getTemplate(),tempalteParams));
        sysNotification.setCreateTime(new Date());
        sysNotification.setExpiryTime(DateTimeUtils.getDate(sysNotification.getCreateTime(),sysNotificationType.getUntilExpiryDay()));
        sysNotification.setDeadTime(DateTimeUtils.getDate(sysNotification.getCreateTime(),sysNotificationType.getUntilDeadDay()));
        sysNotification.setId(Identities.id());
        boolean flag = sysNotification.save();
        if(!flag){
            return  false;
        }
        for( Long receiverId : receivers){
            // 异步 创建 从表数据
            ExecutorServiceUtils.pool.submit(new Runnable() {
                @Override
                public void run() {
                    SysNotificationDetail detail = new SysNotificationDetail();
                    detail.setId(Identities.id());
                    detail.setMainId(sysNotification.getId());
                    detail.setReceiver(receiverId);
                    detail.save();

                    // 子线程发生异常并不会触发 事务回滚
//                    if(Integer.parseInt(receiverId.toString()) % 2==0){
//                        int x = 1/0;
//                    }
                }
            });
        }

        return true;

    }
}
