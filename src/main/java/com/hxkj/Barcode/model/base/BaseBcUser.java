package com.hxkj.Barcode.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * base model generated code
 * @author
 * @date 2018-09-19 11:05:54
 */
@SuppressWarnings("serial")
public abstract class BaseBcUser<M extends BaseBcUser<M>> extends Model<M> implements IBean {

   // idbc_user  
   public void setIdbcUser(java.lang.Integer idbcUser) {
       set("idbc_user", idbcUser);
   }
   public java.lang.Integer getIdbcUser() {
       return get("idbc_user");
   }


   // bc_username  
   public void setBcUsername(java.lang.String bcUsername) {
       set("bc_username", bcUsername);
   }
   public java.lang.String getBcUsername() {
       return get("bc_username");
   }


   // bc_usepw  
   public void setBcUsepw(java.lang.String bcUsepw) {
       set("bc_usepw", bcUsepw);
   }
   public java.lang.String getBcUsepw() {
       return get("bc_usepw");
   }


   // bc_userrole  
   public void setBcUserrole(java.lang.String bcUserrole) {
       set("bc_userrole", bcUserrole);
   }
   public java.lang.String getBcUserrole() {
       return get("bc_userrole");
   }


   // bc_userleve  
   public void setBcUserleve(java.lang.Integer bcUserleve) {
       set("bc_userleve", bcUserleve);
   }
   public java.lang.Integer getBcUserleve() {
       return get("bc_userleve");
   }


   // bc_userdel  
   public void setBcUserdel(java.lang.Integer bcUserdel) {
       set("bc_userdel", bcUserdel);
   }
   public java.lang.Integer getBcUserdel() {
       return get("bc_userdel");
   }


   // createtime  
   public void setCreatetime(java.util.Date createtime) {
       set("createtime", createtime);
   }
   public java.util.Date getCreatetime() {
       return get("createtime");
   }


   // bc_usertoken  
   public void setBcUsertoken(java.lang.String bcUsertoken) {
       set("bc_usertoken", bcUsertoken);
   }
   public java.lang.String getBcUsertoken() {
       return get("bc_usertoken");
   }

}
