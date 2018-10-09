package com.hxkj.Barcode.model;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.hxkj.Barcode.model.base.BaseBcEvents;

/**
 * model table: bc_events   重大事件
 * @author
 * @date 2018-10-09 13:37:35
 */
public class BcEvents extends BaseBcEvents<BcEvents> implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    public static final BcEvents dao = new BcEvents().dao();

    public Page<BcEvents>  page(int pageNumber,int pageSize,String where ){
        String sqlSelect = " select * ";
        String sqlExceptSelect = " from bc_events  ";
        if (StrKit.notBlank(where)) {
            sqlExceptSelect += " where " + where;
        }
        /*jfinal paginate 函数使用 select count() 语句，该函数在百万级别数据效率非常低，数据量大不应该使用jfinal分页封装 */
        return this.paginate(pageNumber,pageSize,sqlSelect,sqlExceptSelect);
    }

}
