package com.hxkj.Barcode.controller;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.tx.Tx;

import com.hxkj.common.constant.Constant;
import com.hxkj.common.controller.BaseController;
import com.hxkj.common.util.Identities;
import com.hxkj.common.util.search.SearchSql;
import com.hxkj.Barcode.model.BcEvents;

/**
 * bc_events 控制器
 * @author
 * @date 2018-10-15 20:30:56
 */
public class BcEventsController extends BaseController{

        /**
         * 列表页
         */
        public void index(){
          render("Barcode/bcEvents.html");
        }


        /**
         * 列表数据
         */
        @Before(SearchSql.class)
        public void query(){
            int pageNumber=getAttr("pageNumber");
            int pageSize=getAttr("pageSize");
            String where=getAttr(Constant.SEARCH_SQL);
            Page<BcEvents> bcEventsPage=BcEvents.dao.page(pageNumber,pageSize,where);
            renderDatagrid(bcEventsPage);
        }


        /**
         * 打开新增或者修改弹出框
         */
        public void newModel(){
            // 有且只有一个主键，且主键类型为 字符串，否则需要手动修改
            String idbc_events=getPara("idbc_events");
            if(StrKit.notBlank(idbc_events)){
                BcEvents bcEvents=BcEvents.dao.findById(idbc_events);
                setAttr("bcEvents",bcEvents);
            }
            render("Barcode/bcEvents_form.html");
        }


        /**
         * 增加
         */
        public void addAction(){
            BcEvents bcEvents=getBean(BcEvents.class,"");
            bcEvents.set("idbc_events",Identities.id());
            boolean saveFlag=bcEvents.save();
            if(saveFlag){
                renderText(Constant.ADD_SUCCESS);
            }else{
                renderText(Constant.ADD_FAIL);
            }
        }

        /**
         * 删除
         */
        @Before(Tx.class)
        public void deleteAction(){
            String idbc_eventss = getPara("idbc_eventss");
            if(idbc_eventss.contains(",")){
                idbc_eventss = idbc_eventss.replaceAll(",","','");
                String deleteSql = "delete from bc_events where idbc_events  in ( '" + idbc_eventss + "' ) ";
                Db.update(deleteSql);
            }else{
                BcEvents.dao.deleteById(idbc_eventss);
            }
            renderText(Constant.DELETE_SUCCESS);
        }

        /**
         * 修改
         */
        public void updateAction(){
            BcEvents bcEvents=getBean(BcEvents.class,"");
            boolean updateFlag=bcEvents.update();
            if(updateFlag){
                renderText(Constant.UPDATE_SUCCESS);
            }else{
                renderText(Constant.UPDATE_FAIL);
            }
        }
}
