package com.hxkj.common.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.hxkj.auth.model.AuthUser;
import com.hxkj.common.constant.Constant;
import com.hxkj.common.interceptor.LoginInterceptor;
import com.hxkj.common.interceptor.PermissionInterceptor;
import com.jfinal.config.*;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.handler.UrlSkipHandler;
import com.jfinal.json.MixedJsonFactory;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.plugin.druid.IDruidStatViewAuth;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;

import javax.servlet.http.HttpServletRequest;

/**
 * Jfinal框架 配置
 *
 * @author zhangcuang
 */
public class AppConfig extends JFinalConfig {

    Prop configProp = PropKit.use("config.properties");

    /**
     * 配置JFinal常量
     *
     * @param me 常量集合
     */
    @Override
    public void configConstant(Constants me) {
        me.setDevMode(configProp.getBoolean("devMode"));

        me.setBaseUploadPath("upload");
        me.setMaxPostSize(10 * 1024 * 1000); // 10M
        me.setBaseDownloadPath("download");

        me.setViewType(ViewType.FREE_MARKER);
        me.setError403View(Constant.VIEW_PATH + "common/403.html");
        me.setError404View(Constant.VIEW_PATH + "common/404.html");
        me.setError500View(Constant.VIEW_PATH + "common/500.html");

        // jfinaljson 和 fastjson 混合
        me.setJsonFactory(MixedJsonFactory.me());
        me.setJsonDatePattern("yyyy-MM-dd HH:mm:ss");
    }


    /**
     * 配置JFinal路由
     *
     * @param me 路由集合
     */
    @Override
    public void configRoute(Routes me) {
        // 权限管理
        me.add(new AuthRoute());
        // 基础数据
        me.add(new DataRoute());
        // 系统运维管理
        me.add(new SysRoute());
        // 公共的，不需要权限控制的路由
        me.add(new CommonRoute());
        // 玩
        me.add(new PlayRoute());
        // cms 管理
        me.add(new cmsRoute());

        // ws
        me.add(new WsRoute());
        
        me.add(new JinRoute());
        me.add(new BarcodeRoute());
    }

    /**
     * 配置 插件
     *
     * @param me 插件集合
     */
    @Override
    public void configPlugin(Plugins me) {
        // 数据源 插件
        DruidPlugin druidPlugin = new DruidPlugin(configProp.get("jdbc.url"), configProp.get("jdbc.user"), configProp.get("jdbc.password"));
        druidPlugin.addFilter(new StatFilter());
        WallFilter wall = new WallFilter();
        wall.setDbType(configProp.get("jdbc.dbType"));
        druidPlugin.addFilter(wall);
        me.add(druidPlugin);

        // active record 插件
        ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(druidPlugin);
        activeRecordPlugin.setShowSql(configProp.getBoolean("devMode"));
        activeRecordPlugin.setDialect(new MysqlDialect());
        MappingKit.mapping(activeRecordPlugin);
        me.add(activeRecordPlugin);
    }

    /**
     * 配置 jfinal interceptor
     *
     * @param me interceptor集合
     */
    @Override
    public void configInterceptor(Interceptors me) {
        me.addGlobalActionInterceptor(new LoginInterceptor());
        me.addGlobalActionInterceptor(new PermissionInterceptor());
    }

    /**
     * 配置 jfinal handler
     *
     * @param me handler集合
     */
    @Override
    public void configHandler(Handlers me) {
        // 跳过处理 ws 请求
        me.add(new UrlSkipHandler("^/ws-server",false));

        // 视图中添加应用context
        me.add(new ContextPathHandler("ctx"));
        // druid 监控（只允许admin查看）
        DruidStatViewHandler dvh = new DruidStatViewHandler("/druid", new IDruidStatViewAuth() {
            public boolean isPermitted(HttpServletRequest request) {
                AuthUser user = (AuthUser) request.getSession().getAttribute(Constant.AUTH_USER);
                if (user == null) {
                    return false;
                }
                return "admin".equals(user.getUsername());
            }
        });
        me.add(dvh);
    }

    @Override
    public void afterJFinalStart() {
    }

    @Override
    public void beforeJFinalStop() {
    }

    @Override
    public void configEngine(Engine me) {
    }
}
