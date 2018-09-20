package com.hxkj.sys.model;

import com.hxkj.sys.model.base.BaseSysNotificationTypeRole;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;

/**
 * model table: sys_notification_type_role   通知类型 角色中间表
 * 用于控制 特定角色用户接收特定类型的系统通知
 *
 * @author
 * @date 2018-09-17 19:18:07
 */
public class SysNotificationTypeRole extends BaseSysNotificationTypeRole<SysNotificationTypeRole> implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    public static final SysNotificationTypeRole dao = new SysNotificationTypeRole().dao();

    public Page<SysNotificationTypeRole> page(int pageNumber, int pageSize, String where) {
        String sqlSelect = " select a.*,c.txt,c.code,  b.role_name, b.role_desc ";
        String sqlExceptSelect = " from sys_notification_type_role a " +
                " left join auth_role b on a.role_id = b.id " +
                " left join sys_notification_type c on a.notification_type_id = c.id ";
        if (StrKit.notBlank(where)) {
            sqlExceptSelect += " where " + where;
        }
        sqlExceptSelect += " order by b.sort ";
        return this.paginate(pageNumber, pageSize, sqlSelect, sqlExceptSelect);
    }

}
