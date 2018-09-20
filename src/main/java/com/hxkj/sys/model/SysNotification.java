package com.hxkj.sys.model;

import com.hxkj.sys.model.base.BaseSysNotification;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * model table: sys_notification   通知主表
 * 过期时间 和 必死 时间 用于控制 表记录行数不过大
 *
 * @author chuang
 * @date 2018-09-17 19:18:06
 */
public class SysNotification extends BaseSysNotification<SysNotification> implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    public static final SysNotification dao = new SysNotification().dao();

    public Page<SysNotification> page(int pageNumber, int pageSize, String where) {
        String sqlSelect = " select * ";
        String sqlExceptSelect = " from sys_notification  ";
        if (StrKit.notBlank(where)) {
            sqlExceptSelect += " where " + where;
        }
        return this.paginate(pageNumber, pageSize, sqlSelect, sqlExceptSelect);
    }



    public Page<Record> findNotificationWithDetailPage(int pageNumber,int pageSize,String where){
        String sqlSelect = " SELECT " +
                " a.id, a.cate1, a.cate2, a.title, a.content, a.create_time, " +
                " b.is_read as has_read, b.read_time as read_time, " +
                " c.name as sender_name, c.id as sender_id,  " +
                " d.name as receiver_name, d.id as receiver_id, " +
                " e.txt as notification_type ";
        String sqlExceptSelect = " FROM sys_notification a " +
                " LEFT JOIN sys_notification_detail b ON b.main_id = a.id " +
                " LEFT JOIN auth_user c on b.sender = c.id " +
                " LEFT JOIN auth_user d on b.receiver = d.id " +
                " LEFT JOIN sys_notification_type e on a.cate2 = e.code  ";
        if (StrKit.notBlank(where)) {
            sqlExceptSelect += " where " + where;
        }
        return Db.paginate(pageNumber, pageSize, sqlSelect, sqlExceptSelect);
    }

}
