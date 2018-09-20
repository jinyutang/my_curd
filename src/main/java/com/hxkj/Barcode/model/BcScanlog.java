package com.hxkj.Barcode.model;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
import com.hxkj.Barcode.model.base.BaseBcScanlog;

/**
 * model table: bc_scanlog   
 * @author
 * @date 2018-09-19 11:05:54
 */
public class BcScanlog extends BaseBcScanlog<BcScanlog> implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    public static final BcScanlog dao = new BcScanlog().dao();

    public Page<BcScanlog> page(int pageNumber,int pageSize,String where ){
        String sqlSelect = " select * ";
        String sqlExceptSelect = " from bc_scanlog  ";
        if (StrKit.notBlank(where)) {
            sqlExceptSelect += " where " + where;
        }
        /*jfinal paginate 函数使用 select count() 语句，该函数在百万级别数据效率非常低，数据量大不应该使用jfinal分页封装 */
        return this.paginate(pageNumber,pageSize,sqlSelect,sqlExceptSelect);
    }
    
    public Page<Record> userScanLog(int pageNumber, int pageSize, String token){
        String sql = " select b.*, s.* from bc_user u, bc_barcode b, bc_scanlog s where u.idbc_user = s.bc_user_idbc_user and s.idbc_scanlog and u.bc_usertoken = " + token;
        SqlPara sqlPara = new SqlPara();
        sqlPara.setSql(sql);
        return Db.paginate(pageNumber, pageSize, sqlPara);
    }

}
