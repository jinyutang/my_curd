package com.hxkj.sys.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * base model generated code
 *
 * @author
 * @date 2018-09-17 19:18:07
 */
@SuppressWarnings("serial")
public abstract class BaseSysNotificationDetail<M extends BaseSysNotificationDetail<M>> extends Model<M> implements IBean {

    // id
    public void setId(String id) {
        set("id", id);
    }

    public String getId() {
        return get("id");
    }


    // main_id  主表id
    public void setMainId(String mainId) {
        set("main_id", mainId);
    }

    public String getMainId() {
        return get("main_id");
    }


    // sender  发送人
    public void setSender(Long sender) {
        set("sender", sender);
    }

    public Long getSender() {
        return get("sender");
    }


    // receiver  接收人
    public void setReceiver(Long receiver) {
        set("receiver", receiver);
    }

    public Long getReceiver() {
        return get("receiver");
    }


    // is_read  是否已读，0未读 1已读
    public void setIsRead(String isRead) {
        set("is_read", isRead);
    }

    public String getIsRead() {
        return get("is_read");
    }


    // read_time  已读时间
    public void setReadTime(java.util.Date readTime) {
        set("read_time", readTime);
    }

    public java.util.Date getReadTime() {
        return get("read_time");
    }

}
