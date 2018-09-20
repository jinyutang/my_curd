package com.hxkj.jin.model;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.hxkj.jin.model.base.BaseJinTb2;

/**
 * model table: jin_tb2   
 * @author
 * @date 2018-09-18 14:37:59
 */
public class JinTb2 extends BaseJinTb2<JinTb2> implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    public static final JinTb2 dao = new JinTb2().dao();

    public Page<JinTb2>  page(int pageNumber,int pageSize,String where ){
        String sqlSelect = " select * ";
        String sqlExceptSelect = " from jin_tb2  ";
        if (StrKit.notBlank(where)) {
            sqlExceptSelect += " where " + where;
        }
        /*jfinal paginate 函数使用 select count() 语句，该函数在百万级别数据效率非常低，数据量大不应该使用jfinal分页封装 */
        return this.paginate(pageNumber,pageSize,sqlSelect,sqlExceptSelect);
    }

}
