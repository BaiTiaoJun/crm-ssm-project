package com.zhangshun.crm.commons.uitl;

public class ConstantUtil {
     //操作成功或者失败返回的标志
     public static final String SUCCESS_CODE = "1";
     public static final String FAIL_CODE = "0";
     //登录验证失败返回信息
     public static final String USER_FAULT_MESSAGE = "用户名或者密码错误";
     public static final String USER_EXPIRE_MESSAGE = "用户已过期";
     public static final String USER_STATUS_MESSAGE = "用户状态被锁定";
     public static final String USER_LOCK_STATE = "0";
     public static final String USER_ALLOW_IPS_MESSAGE = "ip受限";
     //返回界面用户名
     public static final String SESSION_USER = "user";
     //cookie中添加账号和密码的key
     public static final String LOGIN_NAME_KEY = "loginAct";
     public static final String LOGIN_PASSWORD_KEY = "loginPwd";
     //添加市场活动信息失败返回提示信息
     public static final String INSERT_FAIL_MESSAGE = "系统繁忙，请稍后再试";
}
