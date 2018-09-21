package com.hxkj.Barcode.model;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.hxkj.Barcode.model.base.BaseBcUser;

/**
 * model table: bc_user   
 * @author
 * @date 2018-09-21 13:39:33
 */
public class BcUser extends BaseBcUser<BcUser> implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    public static final BcUser dao = new BcUser().dao();

    public Page<BcUser>  page(int pageNumber,int pageSize,String where ){
        String sqlSelect = " select * ";
        String sqlExceptSelect = " from bc_user  ";
        if (StrKit.notBlank(where)) {
            sqlExceptSelect += " where " + where;
        }
        /*jfinal paginate 函数使用 select count() 语句，该函数在百万级别数据效率非常低，数据量大不应该使用jfinal分页封装 */
        return this.paginate(pageNumber,pageSize,sqlSelect,sqlExceptSelect);
    }
    
    public BcUser findByUsernameAndPassword(String username, String password) {
        String sql = "SELECT"
                + " * "
                + " FROM bc_user"
                + " where bc_username = ? and bc_usepw = ?   ";
        return findFirst(sql, username, password);
    }
    
    public BcUser findByToken(String token) {
        String sql = "SELECT"
                + " * "
                + " FROM bc_user"
                + " where bc_usertoken = ? ";
        return findFirst(sql, token);
    }
}
