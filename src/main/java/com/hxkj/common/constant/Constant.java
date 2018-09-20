package com.hxkj.common.constant;

/**
 * 系统的全部常量
 */
public class Constant {

    public final static String DEFAULT_ENCODEING = "UTF-8";

    // 当前系统登录的 用户
    public final static String AUTH_USER = "auth_user";
    public final static String AUTH_USER_NAME = "auth_user_name";

    // 当前登录用户的角色
    public final static String AUTH_USER_ROLES = "auth_user_roles";

    //views 视图 相对路径
    public final static String VIEW_PATH = "/WEB-INF/views/";

    // 当前登录的用户的权限菜单
    public final static String OWN_MENU = "own_menu";

    // 查询 过滤器 使用
    public final static String SEARCH_SQL = "search_sql";

    // 部署服务器 域名（如有防盗链需求）
    public final static String DOMAIN_KEY = "domain_key";

    /* 增、删、改 操作信息 */
    public final static String ADD_SUCCESS = "添加成功";
    public final static String ADD_FAIL = "添加失败";

    public final static String DELETE_SUCCESS = "删除成功";
    public final static String DELETE_FAIL = "删除失败";

    public final static String UPDATE_SUCCESS = "修改成功";
    public final static String UPDATE_FAIL = "修改失败";

    /* APP操作应答 */
    public final static String APP_FAILE = "{\"code\":\"400\"}";
    public final static String APP_SUCCESS = "{\"code\":\"200\"}";
}
