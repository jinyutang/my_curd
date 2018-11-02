package com.hxkj.Barcode.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * base model generated code
 * @author
 * @date 2018-11-02 10:07:37
 */
@SuppressWarnings("serial")
public abstract class BaseBcApp<M extends BaseBcApp<M>> extends Model<M> implements IBean {

   // idbc_app  
   public void setIdbcApp(java.lang.String idbcApp) {
       set("idbc_app", idbcApp);
   }
   public java.lang.String getIdbcApp() {
       return get("idbc_app");
   }


   // versionName  
   public void setVersionName(java.lang.String versionName) {
       set("versionName", versionName);
   }
   public java.lang.String getVersionName() {
       return get("versionName");
   }


   // versionCode  
   public void setVersionCode(java.lang.String versionCode) {
       set("versionCode", versionCode);
   }
   public java.lang.String getVersionCode() {
       return get("versionCode");
   }


   // downloadUrl  
   public void setDownloadUrl(java.lang.String downloadUrl) {
       set("downloadUrl", downloadUrl);
   }
   public java.lang.String getDownloadUrl() {
       return get("downloadUrl");
   }


   // release  
   public void setRelease(java.lang.String release) {
       set("release", release);
   }
   public java.lang.String getRelease() {
       return get("release");
   }


   // comment  
   public void setComment(java.lang.String comment) {
       set("comment", comment);
   }
   public java.lang.String getComment() {
       return get("comment");
   }


   // createtime  
   public void setCreatetime(java.util.Date createtime) {
       set("createtime", createtime);
   }
   public java.util.Date getCreatetime() {
       return get("createtime");
   }

}
