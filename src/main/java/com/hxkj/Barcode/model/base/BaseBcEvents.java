package com.hxkj.Barcode.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * base model generated code
 * @author
 * @date 2018-10-09 13:37:35
 */
@SuppressWarnings("serial")
public abstract class BaseBcEvents<M extends BaseBcEvents<M>> extends Model<M> implements IBean {

   // idbc_events  
   public void setIdbcEvents(java.lang.String idbcEvents) {
       set("idbc_events", idbcEvents);
   }
   public java.lang.String getIdbcEvents() {
       return get("idbc_events");
   }


   // bc_eventname  
   public void setBcEventname(java.lang.String bcEventname) {
       set("bc_eventname", bcEventname);
   }
   public java.lang.String getBcEventname() {
       return get("bc_eventname");
   }


   // bc_eventlocation  
   public void setBcEventlocation(java.lang.String bcEventlocation) {
       set("bc_eventlocation", bcEventlocation);
   }
   public java.lang.String getBcEventlocation() {
       return get("bc_eventlocation");
   }


   // bc_eventtitle  
   public void setBcEventtitle(java.lang.String bcEventtitle) {
       set("bc_eventtitle", bcEventtitle);
   }
   public java.lang.String getBcEventtitle() {
       return get("bc_eventtitle");
   }


   // bc_eventcontent  
   public void setBcEventcontent(java.lang.String bcEventcontent) {
       set("bc_eventcontent", bcEventcontent);
   }
   public java.lang.String getBcEventcontent() {
       return get("bc_eventcontent");
   }


   // bc_eventf1  
   public void setBcEventf1(java.lang.String bcEventf1) {
       set("bc_eventf1", bcEventf1);
   }
   public java.lang.String getBcEventf1() {
       return get("bc_eventf1");
   }


   // bc_eventf2  
   public void setBcEventf2(java.lang.String bcEventf2) {
       set("bc_eventf2", bcEventf2);
   }
   public java.lang.String getBcEventf2() {
       return get("bc_eventf2");
   }


   // bc_eventf3  
   public void setBcEventf3(java.lang.String bcEventf3) {
       set("bc_eventf3", bcEventf3);
   }
   public java.lang.String getBcEventf3() {
       return get("bc_eventf3");
   }


   // bc_eventf4  
   public void setBcEventf4(java.lang.String bcEventf4) {
       set("bc_eventf4", bcEventf4);
   }
   public java.lang.String getBcEventf4() {
       return get("bc_eventf4");
   }


   // bc_eventf5  
   public void setBcEventf5(java.lang.String bcEventf5) {
       set("bc_eventf5", bcEventf5);
   }
   public java.lang.String getBcEventf5() {
       return get("bc_eventf5");
   }


   // bc_eventdel  
   public void setBcEventdel(java.lang.Integer bcEventdel) {
       set("bc_eventdel", bcEventdel);
   }
   public java.lang.Integer getBcEventdel() {
       return get("bc_eventdel");
   }


   // bc_eventcreatetime  
   public void setBcEventcreatetime(java.util.Date bcEventcreatetime) {
       set("bc_eventcreatetime", bcEventcreatetime);
   }
   public java.util.Date getBcEventcreatetime() {
       return get("bc_eventcreatetime");
   }

}
