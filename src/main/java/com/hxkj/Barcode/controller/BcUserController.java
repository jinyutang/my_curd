package com.hxkj.Barcode.controller;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.kit.HashKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.tx.Tx;

import com.hxkj.common.constant.Constant;
import com.hxkj.common.controller.BaseController;
import com.hxkj.common.util.Identities;
import com.hxkj.common.util.search.SearchSql;

import java.util.Date;

import com.hxkj.Barcode.model.BcUser;

/**
 * bc_user 控制器
 * @author
 * @date 2018-09-21 13:39:33
 */
public class BcUserController extends BaseController{

        /**
         * 列表页
         */
        public void index(){
          render("Barcode/bcUser.html");
        }


        /**
         * 列表数据
         */
        @Before(SearchSql.class)
        public void query(){
            int pageNumber=getAttr("pageNumber");
            int pageSize=getAttr("pageSize");
            String where=getAttr(Constant.SEARCH_SQL);
            Page<BcUser> bcUserPage=BcUser.dao.page(pageNumber,pageSize,where);
            renderDatagrid(bcUserPage);
        }


        /**
         * 打开新增或者修改弹出框
         */
        public void newModel(){
            // 有且只有一个主键，且主键类型为 字符串，否则需要手动修改
            String idbc_user=getPara("idbc_user");
            if(StrKit.notBlank(idbc_user)){
                BcUser bcUser=BcUser.dao.findById(idbc_user);
                setAttr("bcUser",bcUser);
            }
            render("Barcode/bcUser_form.html");
        }


        /**
         * 增加
         */
        public void addAction(){
            BcUser bcUser=getBean(BcUser.class,"");
            bcUser.set("idbc_user",Identities.id());
            bcUser.setBcUsepw(HashKit.sha1("123456"));
            bcUser.setCreatetime(new Date());
            boolean saveFlag=bcUser.save();
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
            String idbc_users = getPara("idbc_users");
            if(idbc_users.contains(",")){
                idbc_users = idbc_users.replaceAll(",","','");
                String deleteSql = "delete from bc_user where idbc_user  in ( '" + idbc_users + "' ) ";
                Db.update(deleteSql);
            }else{
                BcUser.dao.deleteById(idbc_users);
            }
            renderText(Constant.DELETE_SUCCESS);
        }

        /**
         * 修改
         */
        public void updateAction(){
            BcUser bcUser=getBean(BcUser.class,"");
            boolean updateFlag=bcUser.update();
            if(updateFlag){
                renderText(Constant.UPDATE_SUCCESS);
            }else{
                renderText(Constant.UPDATE_FAIL);
            }
        }
}
