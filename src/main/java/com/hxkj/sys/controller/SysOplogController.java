package com.hxkj.sys.controller;

import com.hxkj.common.constant.Constant;
import com.hxkj.common.controller.BaseController;
import com.hxkj.common.util.DateTimeUtils;
import com.hxkj.common.util.StringUtils;
import com.hxkj.common.util.csv.CsvRender;
import com.hxkj.common.util.excel.PoiRender;
import com.hxkj.common.util.search.SearchSql;
import com.hxkj.sys.model.SysNotification;
import com.hxkj.sys.model.SysNotificationType;
import com.hxkj.sys.model.SysOplog;
import com.hxkj.sys.service.SysNotificationService;
import com.jfinal.aop.Before;
import com.jfinal.aop.Duang;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * 操作日志
 */
public class SysOplogController extends BaseController {

    private final  static Logger LOG = Logger.getLogger(SysOplogController.class);


    public void index() {
        render("sys/sysOplog.html");
    }

    @Before(SearchSql.class)
    public void query() {
        int pageNumber = getAttr("pageNumber");
        int pageSize = getAttr("pageSize");
        String where = getAttr(Constant.SEARCH_SQL);
        Page<SysOplog> sysOplogPage = SysOplog.dao.page(pageNumber, pageSize, where);
        renderDatagrid(sysOplogPage);
    }


    /**
     * 导出Excel
     */
    @Before(SearchSql.class)
    public void exportToExcel() {
        String where = getAttr(Constant.SEARCH_SQL);
        List<SysOplog> sysOplogs = SysOplog.dao.findWhere(where);
        String[] columns = new String[]{"user_name", "op_content", "ip", "create_time"};
        String[] headers = new String[]{"操作人", "日志内容", "IP地址", "操作时间"};

        render(PoiRender.me(sysOplogs)
                .fileName(StringUtils.urlEncode("操作日志.xls"))
                .sheetName("操作日志统计")
                .columns(columns)
                .headers(headers)
                .cellWidth(3500)
                .headerRow(1));
    }

    @Before(SearchSql.class)
    public void exportToCvs() {
        String where = getAttr(Constant.SEARCH_SQL);
        List<SysOplog> sysOplogs = SysOplog.dao.findWhere(where);
        List<String> headers = Arrays.asList("操作人员", "日志内容", "IP地址", "操作时间");
        List<String> columns = Arrays.asList("user_name", "op_content", "ip", "create_time");
        CsvRender csvRender = new CsvRender(headers, sysOplogs);
        csvRender.fileName("oplog.csv");
        csvRender.clomuns(columns);
        render(csvRender);

    }

    @Before(Tx.class)
    public void deleteAction() {
        String ids = getPara("ids");
        if (ids.contains(",")) {
            ids = "'" + ids.replace(",", "','") + "'";
            String deleteSql = "delete from sys_oplog where id  in ( " + ids + " ) ";
            Db.update(deleteSql);
        } else {
            SysOplog.dao.deleteById(ids);
        }

        // 发送消息通知 （业务并不合适,仅测试用)
        // 此处 sys_notification_type 表数据耦合，不应该 改表数据
        Map<String,Object> templateParams = new HashMap<>();
        templateParams.put("user",getSessionUser().getUsername()); // 操作人
        templateParams.put("date", DateTimeUtils.format(new Date(),DateTimeUtils.pattern_ymd_hms));// 时间
        templateParams.put("number",getPara("ids").split(",").length); // 删除记录条数
        String sysNotificationTypeCode = "00002";
        SysNotification sysNotification = new SysNotification();
        sysNotification.setCate2(sysNotificationTypeCode);
        SysNotificationService service = Duang.duang(SysNotificationService.class);
        boolean flag = service.saveSystemNotificationData(sysNotification,templateParams);
        if(LOG.isInfoEnabled()){
            LOG.info(" 存放通知消息记录："+flag);
        }
        // websocket 通知前台

        renderText(Constant.DELETE_SUCCESS);
    }

}
