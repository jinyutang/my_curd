package com.hxkj.sys.model;

import com.hxkj.sys.model.base.BaseSysNotificationDetail;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;

/**
 * model table: sys_notification_detail   通知表 从表
 *
 * @author
 * @date 2018-09-17 19:18:07
 */
public class SysNotificationDetail extends BaseSysNotificationDetail<SysNotificationDetail> implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    public static final SysNotificationDetail dao = new SysNotificationDetail().dao();

    public Page<SysNotificationDetail> page(int pageNumber, int pageSize, String where) {
        String sqlSelect = " select * ";
        String sqlExceptSelect = " from sys_notification_detail  ";
        if (StrKit.notBlank(where)) {
            sqlExceptSelect += " where " + where;
        }
        /*jfinal paginate 函数使用 select count() 语句，该函数在百万级别数据效率非常低，数据量大不应该使用jfinal分页封装 */
        return this.paginate(pageNumber, pageSize, sqlSelect, sqlExceptSelect);
    }

}
