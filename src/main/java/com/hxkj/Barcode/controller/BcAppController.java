/**
 * 
 */
package com.hxkj.Barcode.controller;

import java.util.HashMap;

import com.hxkj.Barcode.model.BcBarcode;
import com.hxkj.Barcode.model.BcScanlog;
import com.hxkj.Barcode.model.BcUser;
import com.hxkj.common.constant.Constant;
import com.hxkj.common.controller.BaseController;
import com.hxkj.common.util.search.SearchSql;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * @author yutang.jin@finisar.com
 *
 */
@Clear
public class BcAppController extends BaseController {
    @Clear
    public void login() {
        String username = getPara("username", "_");
        String password = getPara("password", "_");
        BcUser bcUser = BcUser.dao.findByUsernameAndPassword(username, password);
        if (null == bcUser) {
            renderJson(Constant.APP_FAILE);
        } else {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("code", 200);
            map.put("data", bcUser);
            renderJson(map);
        }
    }
    @Clear
    public void loginByToken() {
        String token = getPara("token", "_");
        BcUser bcUser = BcUser.dao.findByToken(token);
        if (null == bcUser) {
            renderJson(Constant.APP_FAILE);
        } else {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("code", 200);
            map.put("data", bcUser);
            renderJson(map);
        }
    }
    @Clear
    @Before(SearchSql.class)
    public void userBarcode() {
        String token = getPara("token", "_");
        int pageNumber = getAttr("pageNumber", 1);
        int pageSize = getAttr("pageSize", 50);
        BcUser bcUser = BcUser.dao.findByToken(token);
        String where = getAttr(Constant.SEARCH_SQL);
        if (null == bcUser) {
            renderJson(Constant.APP_FAILE);
        } else {
            HashMap<String, Object> map = new HashMap<String, Object>();
            
            Page<BcBarcode> page = BcBarcode.dao.page(pageNumber, pageSize, where);
            map.put("code", 200);
            map.put("data", page);
            renderJson(map);
        }
    }
    
    @Clear
    @Before(SearchSql.class)
    public void userRole() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("code", 200);
        String[] data = new String[] {"保洁","保安","工程师"};
        map.put("data", data);
        renderJson(map);
    }
    
    @Clear
    @Before(SearchSql.class)
    public void userScanLog() {
        String token = getPara("token", "_");
        int pageNumber = getAttr("pageNumber", 1);
        int pageSize = getAttr("pageSize", 50);
        BcUser bcUser = BcUser.dao.findByToken(token);
        //String where = getAttr(Constant.SEARCH_SQL);
        if (null == bcUser) {
            renderJson(Constant.APP_FAILE);
        } else {
            HashMap<String, Object> map = new HashMap<String, Object>();
            
            Page<Record> page = BcScanlog.dao.userScanLog(pageNumber, pageSize, token);
            map.put("code", 200);
            map.put("data", page);
            renderJson(map);
        }
    }
    
}
