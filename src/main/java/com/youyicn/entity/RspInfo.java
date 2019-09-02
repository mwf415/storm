package com.youyicn.entity;

import java.io.Serializable;

/**
 * @Title RspInfo
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author wangtan
 * @Version 1.0.0
 * @Create 2018/12/4 14:05
 */
public class RspInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;    //返回码
    private String rspMessage;  //返回信息

    private String type;
    private String li;
    private String div;

    @Override
    public String toString() {
        return "RspInfo{" +
                "code='" + code + '\'' +
                ", rspMessage='" + rspMessage + '\'' +
                ", type='" + type + '\'' +
                ", li='" + li + '\'' +
                ", div='" + div + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLi() {
        return li;
    }

    public void setLi(String li) {
        this.li = li;
    }

    public String getDiv() {
        return div;
    }

    public void setDiv(String div) {
        this.div = div;
    }

    public String getCode() {
        if (this.code == null)
            return "200";
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRspMessage() {
        return rspMessage;
    }

    public void setRspMessage(String rspMessage) {
        this.rspMessage = rspMessage;
    }
}
