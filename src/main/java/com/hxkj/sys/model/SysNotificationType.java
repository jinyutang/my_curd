package com.hxkj.sys.model;

import com.hxkj.sys.model.base.BaseSysNotificationType;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;

/**
 * model table: sys_notification_type   系统通知类型
 *
 * @author
 * @date 2018-09-17 19:18:07
 */
public class SysNotificationType extends BaseSysNotificationType<SysNotificationType> implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    public static final SysNotificationType dao = new SysNotificationType().dao();


    public SysNotificationType findByCode(String code){
        return findFirst("select * from sys_notification_type where code = ? ",code);
    }


    public Page<SysNotificationType> page(int pageNumber, int pageSize, String where) {
        String sqlSelect = " select * ";
        String sqlExceptSelect = " from sys_notification_type  ";
        if (StrKit.notBlank(where)) {
            sqlExceptSelect += " where " + where;
        }
        /*jfinal paginate 函数使用 select count() 语句，该函数在百万级别数据效率非常低，数据量大不应该使用jfinal分页封装 */
        return this.paginate(pageNumber, pageSize, sqlSelect, sqlExceptSelect);
    }

}
