package com.hxkj.jin.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * base model generated code
 * @author
 * @date 2018-09-18 14:37:59
 */
@SuppressWarnings("serial")
public abstract class BaseJinTb2<M extends BaseJinTb2<M>> extends Model<M> implements IBean {

   // idjin_tb2  
   public void setIdjinTb2(java.lang.Integer idjinTb2) {
       set("idjin_tb2", idjinTb2);
   }
   public java.lang.Integer getIdjinTb2() {
       return get("idjin_tb2");
   }


   // jin_tb2col  
   public void setJinTb2col(java.lang.Integer jinTb2col) {
       set("jin_tb2col", jinTb2col);
   }
   public java.lang.Integer getJinTb2col() {
       return get("jin_tb2col");
   }


   // jin_tb2col1  
   public void setJinTb2col1(java.lang.String jinTb2col1) {
       set("jin_tb2col1", jinTb2col1);
   }
   public java.lang.String getJinTb2col1() {
       return get("jin_tb2col1");
   }


   // jin_tb2col2  
   public void setJinTb2col2(java.lang.String jinTb2col2) {
       set("jin_tb2col2", jinTb2col2);
   }
   public java.lang.String getJinTb2col2() {
       return get("jin_tb2col2");
   }

}
