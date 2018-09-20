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
import com.hxkj.jin.model.JinTb2;

/**
 * jin_tb2 控制器
 * @author
 * @date 2018-09-18 14:37:59
 */
public class JinTb2Controller extends BaseController{

        /**
         * 列表页
         */
        public void index(){
          render("jin/jinTb2.html");
        }


        /**
         * 列表数据
         */
        @Before(SearchSql.class)
        public void query(){
            int pageNumber=getAttr("pageNumber");
            int pageSize=getAttr("pageSize");
            String where=getAttr(Constant.SEARCH_SQL);
            Page<JinTb2> jinTb2Page=JinTb2.dao.page(pageNumber,pageSize,where);
            renderDatagrid(jinTb2Page);
        }


        /**
         * 打开新增或者修改弹出框
         */
        public void newModel(){
            // 有且只有一个主键，且主键类型为 字符串，否则需要手动修改
            String idjin_tb2=getPara("idjin_tb2");
            if(StrKit.notBlank(idjin_tb2)){
                JinTb2 jinTb2=JinTb2.dao.findById(idjin_tb2);
                setAttr("jinTb2",jinTb2);
            }
            render("jin/jinTb2_form.html");
        }


        /**
         * 增加
         */
        public void addAction(){
            JinTb2 jinTb2=getBean(JinTb2.class,"");
            jinTb2.set("idjin_tb2",Identities.id());
            boolean saveFlag=jinTb2.save();
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
            String idjin_tb2s = getPara("idjin_tb2s");
            if(idjin_tb2s.contains(",")){
                idjin_tb2s = idjin_tb2s.replaceAll(",","','");
                String deleteSql = "delete from jin_tb2 where idjin_tb2  in ( '" + idjin_tb2s + "' ) ";
                Db.update(deleteSql);
            }else{
                JinTb2.dao.deleteById(idjin_tb2s);
            }
            renderText(Constant.DELETE_SUCCESS);
        }

        /**
         * 修改
         */
        public void updateAction(){
            JinTb2 jinTb2=getBean(JinTb2.class,"");
            boolean updateFlag=jinTb2.update();
            if(updateFlag){
                renderText(Constant.UPDATE_SUCCESS);
            }else{
                renderText(Constant.UPDATE_FAIL);
            }
        }
}
