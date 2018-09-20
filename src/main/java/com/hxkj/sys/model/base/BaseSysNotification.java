package com.hxkj.sys.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * base model generated code
 *
 * @author
 * @date 2018-09-17 19:18:06
 */
@SuppressWarnings("serial")
public abstract class BaseSysNotification<M extends BaseSysNotification<M>> extends Model<M> implements IBean {

    // id
    public void setId(String id) {
        set("id", id);
    }

    public String getId() {
        return get("id");
    }


    // cate1  大分类，USER 和 SYSTEM 两种
    public void setCate1(String cate1) {
        set("cate1", cate1);
    }

    public String getCate1() {
        return get("cate1");
    }


    // cate2  小分类，SYSTEM通知类型下的业务代码
    public void setCate2(String cate2) {
        set("cate2", cate2);
    }

    public String getCate2() {
        return get("cate2");
    }


    // title  消息标题
    public void setTitle(String title) {
        set("title", title);
    }

    public String getTitle() {
        return get("title");
    }


    // content  消息内容
    public void setContent(String content) {
        set("content", content);
    }

    public String getContent() {
        return get("content");
    }


    // create_time  创建时间
    public void setCreateTime(java.util.Date createTime) {
        set("create_time", createTime);
    }

    public java.util.Date getCreateTime() {
        return get("create_time");
    }


    // expiry_time  过期时间
    public void setExpiryTime(java.util.Date expiryTime) {
        set("expiry_time", expiryTime);
    }

    public java.util.Date getExpiryTime() {
        return get("expiry_time");
    }


    // dead_time  必死时间
    public void setDeadTime(java.util.Date deadTime) {
        set("dead_time", deadTime);
    }

    public java.util.Date getDeadTime() {
        return get("dead_time");
    }

}
