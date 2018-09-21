package com.hxkj.Barcode.model;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.hxkj.Barcode.model.base.BaseBcBarcode;

/**
 * model table: bc_barcode   
 * @author
 * @date 2018-09-21 13:39:33
 */
public class BcBarcode extends BaseBcBarcode<BcBarcode> implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    public static final BcBarcode dao = new BcBarcode().dao();

    public Page<BcBarcode>  page(int pageNumber,int pageSize,String where ){
        String sqlSelect = " select * ";
        String sqlExceptSelect = " from bc_barcode  ";
        if (StrKit.notBlank(where)) {
            sqlExceptSelect += " where " + where;
        }
        /*jfinal paginate 函数使用 select count() 语句，该函数在百万级别数据效率非常低，数据量大不应该使用jfinal分页封装 */
        return this.paginate(pageNumber,pageSize,sqlSelect,sqlExceptSelect);
    }
    
}
