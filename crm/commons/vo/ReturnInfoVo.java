package com.zhangshun.crm.commons.vo;

public class ReturnInfoVo {
    private String code;
    private String message;
    private Object returnRes;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getReturnRes() {
        return returnRes;
    }

    public void setReturnRes(Object returnRes) {
        this.returnRes = returnRes;
    }
}