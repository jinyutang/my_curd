package com.hxkj.sys.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * base model generated code
 *
 * @author
 * @date 2018-09-18 18:29:09
 */
@SuppressWarnings("serial")
public abstract class BaseSysNotificationType<M extends BaseSysNotificationType<M>> extends Model<M> implements IBean {

    // id
    public void setId(java.lang.String id) {
        set("id", id);
    }

    public java.lang.String getId() {
        return get("id");
    }


    // txt  名字
    public void setTxt(java.lang.String txt) {
        set("txt", txt);
    }

    public java.lang.String getTxt() {
        return get("txt");
    }


    // code  编码
    public void setCode(java.lang.String code) {
        set("code", code);
    }

    public java.lang.String getCode() {
        return get("code");
    }

    // tempalte 通知模板
    public void setTemplate(java.lang.String template) {
        set("template", template);
    }

    public java.lang.String getTemplate() {
        return get("template");
    }

    // remark  备注
    public void setRemark(java.lang.String remark) {
        set("remark", remark);
    }

    public java.lang.String getRemark() {
        return get("remark");
    }


    // until_expiry_day  多少天后过期
    public void setUntilExpiryDay(java.lang.Integer untilExpiryDay) {
        set("until_expiry_day", untilExpiryDay);
    }

    public java.lang.Integer getUntilExpiryDay() {
        return get("until_expiry_day");
    }


    // until_dead_day  多少天后必死
    public void setUntilDeadDay(java.lang.Integer untilDeadDay) {
        set("until_dead_day", untilDeadDay);
    }

    public java.lang.Integer getUntilDeadDay() {
        return get("until_dead_day");
    }

}
