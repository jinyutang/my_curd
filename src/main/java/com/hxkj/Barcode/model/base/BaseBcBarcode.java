package com.hxkj.Barcode.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * base model generated code
 * @author
 * @date 2018-09-21 13:39:33
 */
@SuppressWarnings("serial")
public abstract class BaseBcBarcode<M extends BaseBcBarcode<M>> extends Model<M> implements IBean {

   // idbc_barcode  
   public void setIdbcBarcode(java.lang.String idbcBarcode) {
       set("idbc_barcode", idbcBarcode);
   }
   public java.lang.String getIdbcBarcode() {
       return get("idbc_barcode");
   }


   // bc_barcodesn  
   public void setBcBarcodesn(java.lang.String bcBarcodesn) {
       set("bc_barcodesn", bcBarcodesn);
   }
   public java.lang.String getBcBarcodesn() {
       return get("bc_barcodesn");
   }


   // bc_barcodename  
   public void setBcBarcodename(java.lang.String bcBarcodename) {
       set("bc_barcodename", bcBarcodename);
   }
   public java.lang.String getBcBarcodename() {
       return get("bc_barcodename");
   }


   // bc_barcodelocation  
   public void setBcBarcodelocation(java.lang.String bcBarcodelocation) {
       set("bc_barcodelocation", bcBarcodelocation);
   }
   public java.lang.String getBcBarcodelocation() {
       return get("bc_barcodelocation");
   }


   // bc_barcoderole  
   public void setBcBarcoderole(java.lang.String bcBarcoderole) {
       set("bc_barcoderole", bcBarcoderole);
   }
   public java.lang.String getBcBarcoderole() {
       return get("bc_barcoderole");
   }


   // bc_barcodeleve  
   public void setBcBarcodeleve(java.lang.Integer bcBarcodeleve) {
       set("bc_barcodeleve", bcBarcodeleve);
   }
   public java.lang.Integer getBcBarcodeleve() {
       return get("bc_barcodeleve");
   }


   // bc_barcodetype  
   public void setBcBarcodetype(java.lang.Integer bcBarcodetype) {
       set("bc_barcodetype", bcBarcodetype);
   }
   public java.lang.Integer getBcBarcodetype() {
       return get("bc_barcodetype");
   }


   // bc_barcodedescription  
   public void setBcBarcodedescription(java.lang.String bcBarcodedescription) {
       set("bc_barcodedescription", bcBarcodedescription);
   }
   public java.lang.String getBcBarcodedescription() {
       return get("bc_barcodedescription");
   }

   // bc_barcodeimage  
   public void setBcBarcodeimg(java.lang.String bcBarcodeimg) {
       set("bc_barcodeimg", bcBarcodeimg);
   }
   public java.lang.String getBcBarcodeimg() {
       return get("bc_barcodeimg");
   }


   // bc_barcodedel  
   public void setBcBarcodedel(java.lang.Integer bcBarcodedel) {
       set("bc_barcodedel", bcBarcodedel);
   }
   public java.lang.Integer getBcBarcodedel() {
       return get("bc_barcodedel");
   }

}
