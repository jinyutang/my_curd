package com.hxkj.Barcode.model;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.hxkj.Barcode.model.base.BaseBcApp;

/**
 * model table: bc_app   app更新下载
 * @author
 * @date 2018-11-02 10:07:37
 */
public class BcApp extends BaseBcApp<BcApp> implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    public static final BcApp dao = new BcApp().dao();

    public Page<BcApp>  page(int pageNumber,int pageSize,String where ){
        String sqlSelect = " select * ";
        String sqlExceptSelect = " from bc_app  ";
        if (StrKit.notBlank(where)) {
            sqlExceptSelect += " where " + where;
        }
        /*jfinal paginate 函数使用 select count() 语句，该函数在百万级别数据效率非常低，数据量大不应该使用jfinal分页封装 */
        return this.paginate(pageNumber,pageSize,sqlSelect,sqlExceptSelect);
    }

}
