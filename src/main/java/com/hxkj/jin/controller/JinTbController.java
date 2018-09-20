package com.hxkj.jin.controller;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.tx.Tx;

import com.hxkj.common.constant.Constant;
import com.hxkj.common.controller.BaseController;
import com.hxkj.common.util.Identities;
import com.hxkj.common.util.search.SearchSql;
import com.hxkj.jin.model.JinTb;

/**
 * jin_tb 控制器
 * @author
 * @date 2018-09-18 14:37:59
 */
public class JinTbController extends BaseController{

        /**
         * 列表页
         */
        public void index(){
          render("jin/jinTb.html");
        }


        /**
         * 列表数据
         */
        @Before(SearchSql.class)
        public void query(){
            int pageNumber=getAttr("pageNumber");
            int pageSize=getAttr("pageSize");
            String where=getAttr(Constant.SEARCH_SQL);
            Page<JinTb> jinTbPage=JinTb.dao.page(pageNumber,pageSize,where);
            renderDatagrid(jinTbPage);
        }


        /**
         * 打开新增或者修改弹出框
         */
        public void newModel(){
            // 有且只有一个主键，且主键类型为 字符串，否则需要手动修改
            String idjin_tb=getPara("idjin_tb");
            if(StrKit.notBlank(idjin_tb)){
                JinTb jinTb=JinTb.dao.findById(idjin_tb);
                setAttr("jinTb",jinTb);
            }
            render("jin/jinTb_form.html");
        }


        /**
         * 增加
         */
        public void addAction(){
            JinTb jinTb=getBean(JinTb.class,"");
            jinTb.set("idjin_tb",Identities.id());
            boolean saveFlag=jinTb.save();
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
            String idjin_tbs = getPara("idjin_tbs");
            if(idjin_tbs.contains(",")){
                idjin_tbs = idjin_tbs.replaceAll(",","','");
                String deleteSql = "delete from jin_tb where idjin_tb  in ( '" + idjin_tbs + "' ) ";
                Db.update(deleteSql);
            }else{
                JinTb.dao.deleteById(idjin_tbs);
            }
            renderText(Constant.DELETE_SUCCESS);
        }

        /**
         * 修改
         */
        public void updateAction(){
            JinTb jinTb=getBean(JinTb.class,"");
            boolean updateFlag=jinTb.update();
            if(updateFlag){
                renderText(Constant.UPDATE_SUCCESS);
            }else{
                renderText(Constant.UPDATE_FAIL);
            }
        }
}
