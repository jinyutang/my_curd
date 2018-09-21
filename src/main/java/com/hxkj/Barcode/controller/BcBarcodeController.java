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
import com.hxkj.Barcode.model.BcBarcode;

/**
 * bc_barcode 控制器
 * @author
 * @date 2018-09-21 13:39:33
 */
public class BcBarcodeController extends BaseController{

        /**
         * 列表页
         */
        public void index(){
          render("Barcode/bcBarcode.html");
        }


        /**
         * 列表数据
         */
        @Before(SearchSql.class)
        public void query(){
            int pageNumber=getAttr("pageNumber");
            int pageSize=getAttr("pageSize");
            String where=getAttr(Constant.SEARCH_SQL);
            Page<BcBarcode> bcBarcodePage=BcBarcode.dao.page(pageNumber,pageSize,where);
            renderDatagrid(bcBarcodePage);
        }


        /**
         * 打开新增或者修改弹出框
         */
        public void newModel(){
            // 有且只有一个主键，且主键类型为 字符串，否则需要手动修改
            String idbc_barcode=getPara("idbc_barcode");
            if(StrKit.notBlank(idbc_barcode)){
                BcBarcode bcBarcode=BcBarcode.dao.findById(idbc_barcode);
                setAttr("bcBarcode",bcBarcode);
            }
            render("Barcode/bcBarcode_form.html");
        }


        /**
         * 增加
         */
        public void addAction(){
            BcBarcode bcBarcode=getBean(BcBarcode.class,"");
            bcBarcode.set("idbc_barcode",Identities.id());
            boolean saveFlag=bcBarcode.save();
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
            String idbc_barcodes = getPara("idbc_barcodes");
            if(idbc_barcodes.contains(",")){
                idbc_barcodes = idbc_barcodes.replaceAll(",","','");
                String deleteSql = "delete from bc_barcode where idbc_barcode  in ( '" + idbc_barcodes + "' ) ";
                Db.update(deleteSql);
            }else{
                BcBarcode.dao.deleteById(idbc_barcodes);
            }
            renderText(Constant.DELETE_SUCCESS);
        }

        /**
         * 修改
         */
        public void updateAction(){
            BcBarcode bcBarcode=getBean(BcBarcode.class,"");
            boolean updateFlag=bcBarcode.update();
            if(updateFlag){
                renderText(Constant.UPDATE_SUCCESS);
            }else{
                renderText(Constant.UPDATE_FAIL);
            }
        }
}
