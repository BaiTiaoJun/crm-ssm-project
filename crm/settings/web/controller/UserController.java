package com.zhangshun.crm.settings.web.controller;

import com.zhangshun.crm.commons.uitl.MD5Util;
import com.zhangshun.crm.commons.vo.ReturnInfoVo;
import com.zhangshun.crm.settings.service.UserService;
import com.zhangshun.crm.commons.uitl.ConstantUtil;
import com.zhangshun.crm.commons.uitl.DateFormatUtil;
import com.zhangshun.crm.settings.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin() {
        return "settings/qx/user/login";
    }

    @RequestMapping("/settings/qx/user/login.do")
    public @ResponseBody Object login(String loginAct, String loginPwd, String isRemPwd, HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        ReturnInfoVo returnInfoVo = new ReturnInfoVo();

        Map<String, Object> map = new HashMap<>();
        map.put("loginAct", loginAct);
        map.put("loginPwd", MD5Util.getMD5(loginPwd));

        User user = userService.queryUserByLoginActAndPwd(map);
        if (user == null) {
            //user为空，用户不存在
            returnInfoVo.setCode(ConstantUtil.FAIL_CODE);
            returnInfoVo.setMessage(ConstantUtil.USER_FAULT_MESSAGE);
        } else {
            if (DateFormatUtil.getDateFormat_FULL(new Date()).compareTo(user.getExpiretime()) > 0) {
                //用户已过期
                returnInfoVo.setCode(ConstantUtil.FAIL_CODE);
                returnInfoVo.setMessage(ConstantUtil.USER_EXPIRE_MESSAGE);
            } else if (ConstantUtil.USER_LOCK_STATE.equals(user.getLockstate())) {
                //用户状态被锁定
                returnInfoVo.setCode(ConstantUtil.FAIL_CODE);
                returnInfoVo.setMessage(ConstantUtil.USER_STATUS_MESSAGE);
            } else if (!user.getAllowips().contains(request.getRemoteAddr())) {
                //ip受限
                returnInfoVo.setCode(ConstantUtil.FAIL_CODE);
                returnInfoVo.setMessage(ConstantUtil.USER_ALLOW_IPS_MESSAGE);
            } else {
                //登录成功
                returnInfoVo.setCode(ConstantUtil.SUCCESS_CODE);
                //返回用户名称到浏览器
                session.setAttribute(ConstantUtil.SESSION_USER, user);

                Cookie cookieUserName = new Cookie(ConstantUtil.LOGIN_NAME_KEY, loginAct);
                Cookie cookiePassword = new Cookie(ConstantUtil.LOGIN_PASSWORD_KEY, loginPwd);
                //判断是否记住账户和密码
                if ("true".equals(isRemPwd)) {
                    //保存十天
                    cookieUserName.setMaxAge(10 * 24 * 60 * 60);
                    cookiePassword.setMaxAge(10 * 24 * 60 * 60);
                } else {
                    //保存为0秒，相当于销毁cookie
                    cookieUserName.setMaxAge(0);
                    cookiePassword.setMaxAge(0);
                }
                response.addCookie(cookieUserName);
                response.addCookie(cookiePassword);
            }
        }
        return returnInfoVo;
    }

    @RequestMapping("/settings/qx/user/toLogout.do")
    public String toLogout(HttpSession session, HttpServletResponse response) {
        //返回一个生命周期为0秒的cookie,清空登录信息栏的账号和密码
        Cookie cookieUserName = new Cookie(ConstantUtil.LOGIN_NAME_KEY, "");
        Cookie cookiePassword = new Cookie(ConstantUtil.LOGIN_PASSWORD_KEY, "");
        cookieUserName.setMaxAge(0);
        cookiePassword.setMaxAge(0);
        response.addCookie(cookieUserName);
        response.addCookie(cookiePassword);
        //销毁服务端的session
        session.invalidate();
        //转发到项目的根目录-->indexController-->登录界面
        return "redirect:/";
    }
}