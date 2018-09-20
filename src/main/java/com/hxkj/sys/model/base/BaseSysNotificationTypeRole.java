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
public abstract class BaseSysNotificationTypeRole<M extends BaseSysNotificationTypeRole<M>> extends Model<M> implements IBean {

    // notification_type_id  系统通知类型id
    public void setNotificationTypeId(String notificationTypeId) {
        set("notification_type_id", notificationTypeId);
    }

    public String getNotificationTypeId() {
        return get("notification_type_id");
    }


    // role_id  角色类型id
    public void setRoleId(Long roleId) {
        set("role_id", roleId);
    }

    public Long getRoleId() {
        return get("role_id");
    }

}
