package com.hxkj.Barcode.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * base model generated code
 * @author
 * @date 2018-09-21 13:39:33
 */
@SuppressWarnings("serial")
public abstract class BaseBcScanlog<M extends BaseBcScanlog<M>> extends Model<M> implements IBean {

   // idbc_scanlog  
   public void setIdbcScanlog(java.lang.String idbcScanlog) {
       set("idbc_scanlog", idbcScanlog);
   }
   public java.lang.String getIdbcScanlog() {
       return get("idbc_scanlog");
   }


   // bc_scanlogcreatetime  
   public void setBcScanlogcreatetime(java.util.Date bcScanlogcreatetime) {
       set("bc_scanlogcreatetime", bcScanlogcreatetime);
   }
   public java.util.Date getBcScanlogcreatetime() {
       return get("bc_scanlogcreatetime");
   }


   // bc_scanlogstatus  
   public void setBcScanlogstatus(java.lang.String bcScanlogstatus) {
       set("bc_scanlogstatus", bcScanlogstatus);
   }
   public java.lang.String getBcScanlogstatus() {
       return get("bc_scanlogstatus");
   }


   // bc_scanlogimg1  
   public void setBcScanlogimg1(java.lang.String bcScanlogimg1) {
       set("bc_scanlogimg1", bcScanlogimg1);
   }
   public java.lang.String getBcScanlogimg1() {
       return get("bc_scanlogimg1");
   }


   // bc_scanlogimg2  
   public void setBcScanlogimg2(java.lang.String bcScanlogimg2) {
       set("bc_scanlogimg2", bcScanlogimg2);
   }
   public java.lang.String getBcScanlogimg2() {
       return get("bc_scanlogimg2");
   }


   // bc_scanlogimg3  
   public void setBcScanlogimg3(java.lang.String bcScanlogimg3) {
       set("bc_scanlogimg3", bcScanlogimg3);
   }
   public java.lang.String getBcScanlogimg3() {
       return get("bc_scanlogimg3");
   }


   // bc_scanlogimg4  
   public void setBcScanlogimg4(java.lang.String bcScanlogimg4) {
       set("bc_scanlogimg4", bcScanlogimg4);
   }
   public java.lang.String getBcScanlogimg4() {
       return get("bc_scanlogimg4");
   }


   // bc_scanlogimg5  
   public void setBcScanlogimg5(java.lang.String bcScanlogimg5) {
       set("bc_scanlogimg5", bcScanlogimg5);
   }
   public java.lang.String getBcScanlogimg5() {
       return get("bc_scanlogimg5");
   }


   // bc_scanlogcontent1  
   public void setBcScanlogcontent1(java.lang.String bcScanlogcontent1) {
       set("bc_scanlogcontent1", bcScanlogcontent1);
   }
   public java.lang.String getBcScanlogcontent1() {
       return get("bc_scanlogcontent1");
   }


   // bc_scanlogcontent2  
   public void setBcScanlogcontent2(java.lang.String bcScanlogcontent2) {
       set("bc_scanlogcontent2", bcScanlogcontent2);
   }
   public java.lang.String getBcScanlogcontent2() {
       return get("bc_scanlogcontent2");
   }


   // bc_scanlogcontent3  
   public void setBcScanlogcontent3(java.lang.String bcScanlogcontent3) {
       set("bc_scanlogcontent3", bcScanlogcontent3);
   }
   public java.lang.String getBcScanlogcontent3() {
       return get("bc_scanlogcontent3");
   }


   // bc_scanlogcontent4  
   public void setBcScanlogcontent4(java.lang.String bcScanlogcontent4) {
       set("bc_scanlogcontent4", bcScanlogcontent4);
   }
   public java.lang.String getBcScanlogcontent4() {
       return get("bc_scanlogcontent4");
   }


   // bc_scanlogcontent5  
   public void setBcScanlogcontent5(java.lang.String bcScanlogcontent5) {
       set("bc_scanlogcontent5", bcScanlogcontent5);
   }
   public java.lang.String getBcScanlogcontent5() {
       return get("bc_scanlogcontent5");
   }


   // bc_user_idbc_user  
   public void setBcUserIdbcUser(java.lang.String bcUserIdbcUser) {
       set("bc_user_idbc_user", bcUserIdbcUser);
   }
   public java.lang.String getBcUserIdbcUser() {
       return get("bc_user_idbc_user");
   }


   // bc_barcode_idbc_barcode  
   public void setBcBarcodeIdbcBarcode(java.lang.String bcBarcodeIdbcBarcode) {
       set("bc_barcode_idbc_barcode", bcBarcodeIdbcBarcode);
   }
   public java.lang.String getBcBarcodeIdbcBarcode() {
       return get("bc_barcode_idbc_barcode");
   }

}
