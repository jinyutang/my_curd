package com.hxkj.jin.model;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.hxkj.jin.model.base.BaseJinTb;

/**
 * model table: jin_tb   
 * @author
 * @date 2018-09-18 14:37:59
 */
public class JinTb extends BaseJinTb<JinTb> implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    public static final JinTb dao = new JinTb().dao();

    public Page<JinTb>  page(int pageNumber,int pageSize,String where ){
        String sqlSelect = " select * ";
        String sqlExceptSelect = " from jin_tb  ";
        if (StrKit.notBlank(where)) {
            sqlExceptSelect += " where " + where;
        }
        /*jfinal paginate 函数使用 select count() 语句，该函数在百万级别数据效率非常低，数据量大不应该使用jfinal分页封装 */
        return this.paginate(pageNumber,pageSize,sqlSelect,sqlExceptSelect);
    }

}
