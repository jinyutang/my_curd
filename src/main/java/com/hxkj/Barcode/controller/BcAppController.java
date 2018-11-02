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
import com.hxkj.Barcode.model.BcApp;

/**
 * bc_app 控制器
 * @author
 * @date 2018-11-02 10:07:37
 */
public class BcAppController extends BaseController{

        /**
         * 列表页
         */
        public void index(){
          render("Barcode/bcApp.html");
        }


        /**
         * 列表数据
         */
        @Before(SearchSql.class)
        public void query(){
            int pageNumber=getAttr("pageNumber");
            int pageSize=getAttr("pageSize");
            String where=getAttr(Constant.SEARCH_SQL);
            Page<BcApp> bcAppPage=BcApp.dao.page(pageNumber,pageSize,where);
            renderDatagrid(bcAppPage);
        }


        /**
         * 打开新增或者修改弹出框
         */
        public void newModel(){
            // 有且只有一个主键，且主键类型为 字符串，否则需要手动修改
            String idbc_app=getPara("idbc_app");
            if(StrKit.notBlank(idbc_app)){
                BcApp bcApp=BcApp.dao.findById(idbc_app);
                setAttr("bcApp",bcApp);
            }
            render("Barcode/bcApp_form.html");
        }


        /**
         * 增加
         */
        public void addAction(){
            BcApp bcApp=getBean(BcApp.class,"");
            bcApp.set("idbc_app",Identities.id());
            boolean saveFlag=bcApp.save();
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
            String idbc_apps = getPara("idbc_apps");
            if(idbc_apps.contains(",")){
                idbc_apps = idbc_apps.replaceAll(",","','");
                String deleteSql = "delete from bc_app where idbc_app  in ( '" + idbc_apps + "' ) ";
                Db.update(deleteSql);
            }else{
                BcApp.dao.deleteById(idbc_apps);
            }
            renderText(Constant.DELETE_SUCCESS);
        }

        /**
         * 修改
         */
        public void updateAction(){
            BcApp bcApp=getBean(BcApp.class,"");
            boolean updateFlag=bcApp.update();
            if(updateFlag){
                renderText(Constant.UPDATE_SUCCESS);
            }else{
                renderText(Constant.UPDATE_FAIL);
            }
        }
}
