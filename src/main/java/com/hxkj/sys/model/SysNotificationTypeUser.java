package com.hxkj.sys.model;

import com.hxkj.sys.model.base.BaseSysNotificationTypeUser;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;

/**
 * model table: sys_notification_type_user   通知类型 用户中间表
 * 用户控制特定用户接受特定类型的通知
 *
 * @author
 * @date 2018-09-17 19:18:07
 */
public class SysNotificationTypeUser extends BaseSysNotificationTypeUser<SysNotificationTypeUser> implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    public static final SysNotificationTypeUser dao = new SysNotificationTypeUser().dao();

    public Page<SysNotificationTypeUser> page(int pageNumber, int pageSize, String where) {
        String sqlSelect = " SELECT  a.* , b.username, b.name, b.job, c.name AS org_name, d.code  ";
        String sqlExceptSelect = " FROM sys_notification_type_user a " +
                " LEFT JOIN auth_user b ON a.user_id = b.id " +
                " LEFT JOIN auth_org c ON b.org_id = c.id " +
                " LEFT JOIN sys_notification_type d ON a.notification_type_id = d.id  ";
        if (StrKit.notBlank(where)) {
            sqlExceptSelect += " where " + where;
        }
        return this.paginate(pageNumber, pageSize, sqlSelect, sqlExceptSelect);
    }

}
