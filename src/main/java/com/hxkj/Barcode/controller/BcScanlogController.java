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
import com.hxkj.Barcode.model.BcScanlog;

/**
 * bc_scanlog 控制器
 * @author
 * @date 2018-09-19 11:05:54
 */
public class BcScanlogController extends BaseController{

        /**
         * 列表页
         */
        public void index(){
          render("Barcode/bcScanlog.html");
        }


        /**
         * 列表数据
         */
        @Before(SearchSql.class)
        public void query(){
            int pageNumber=getAttr("pageNumber");
            int pageSize=getAttr("pageSize");
            String where=getAttr(Constant.SEARCH_SQL);
            Page<BcScanlog> bcScanlogPage=BcScanlog.dao.page(pageNumber,pageSize,where);
            renderDatagrid(bcScanlogPage);
        }


        /**
         * 打开新增或者修改弹出框
         */
        public void newModel(){
            // 有且只有一个主键，且主键类型为 字符串，否则需要手动修改
            String idbc_scanlog=getPara("idbc_scanlog");
            if(StrKit.notBlank(idbc_scanlog)){
                BcScanlog bcScanlog=BcScanlog.dao.findById(idbc_scanlog);
                setAttr("bcScanlog",bcScanlog);
            }
            render("Barcode/bcScanlog_form.html");
        }


        /**
         * 增加
         */
        public void addAction(){
            BcScanlog bcScanlog=getBean(BcScanlog.class,"");
            bcScanlog.set("idbc_scanlog",Identities.id());
            boolean saveFlag=bcScanlog.save();
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
            String idbc_scanlogs = getPara("idbc_scanlogs");
            if(idbc_scanlogs.contains(",")){
                idbc_scanlogs = idbc_scanlogs.replaceAll(",","','");
                String deleteSql = "delete from bc_scanlog where idbc_scanlog  in ( '" + idbc_scanlogs + "' ) ";
                Db.update(deleteSql);
            }else{
                BcScanlog.dao.deleteById(idbc_scanlogs);
            }
            renderText(Constant.DELETE_SUCCESS);
        }

        /**
         * 修改
         */
        public void updateAction(){
            BcScanlog bcScanlog=getBean(BcScanlog.class,"");
            boolean updateFlag=bcScanlog.update();
            if(updateFlag){
                renderText(Constant.UPDATE_SUCCESS);
            }else{
                renderText(Constant.UPDATE_FAIL);
            }
        }
}
