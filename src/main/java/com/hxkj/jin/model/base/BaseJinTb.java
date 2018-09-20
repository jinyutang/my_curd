package com.hxkj.jin.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * base model generated code
 * @author
 * @date 2018-09-18 14:37:59
 */
@SuppressWarnings("serial")
public abstract class BaseJinTb<M extends BaseJinTb<M>> extends Model<M> implements IBean {

   // idjin_tb  
   public void setIdjinTb(java.lang.Integer idjinTb) {
       set("idjin_tb", idjinTb);
   }
   public java.lang.Integer getIdjinTb() {
       return get("idjin_tb");
   }


   // jin_tbcol  
   public void setJinTbcol(java.lang.String jinTbcol) {
       set("jin_tbcol", jinTbcol);
   }
   public java.lang.String getJinTbcol() {
       return get("jin_tbcol");
   }


   // jin_tbcol1  
   public void setJinTbcol1(java.lang.String jinTbcol1) {
       set("jin_tbcol1", jinTbcol1);
   }
   public java.lang.String getJinTbcol1() {
       return get("jin_tbcol1");
   }


   // jin_tbcol2  
   public void setJinTbcol2(java.lang.String jinTbcol2) {
       set("jin_tbcol2", jinTbcol2);
   }
   public java.lang.String getJinTbcol2() {
       return get("jin_tbcol2");
   }


   // jin_tbcol3  
   public void setJinTbcol3(java.util.Date jinTbcol3) {
       set("jin_tbcol3", jinTbcol3);
   }
   public java.util.Date getJinTbcol3() {
       return get("jin_tbcol3");
   }

}
