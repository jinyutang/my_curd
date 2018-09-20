package com.hxkj.sys.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * base model generated code
 *
 * @author
 * @date 2018-09-17 19:18:07
 */
@SuppressWarnings("serial")
public abstract class BaseSysNotificationTypeUser<M extends BaseSysNotificationTypeUser<M>> extends Model<M> implements IBean {

    // notification_type_id  系统通知类型id
    public void setNotificationTypeId(String notificationTypeId) {
        set("notification_type_id", notificationTypeId);
    }

    public String getNotificationTypeId() {
        return get("notification_type_id");
    }


    // user_id  用户id
    public void setUserId(Long userId) {
        set("user_id", userId);
    }

    public Long getUserId() {
        return get("user_id");
    }

}
