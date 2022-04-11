package com.zhangshun.crm.workbench.web.controller;

import com.zhangshun.crm.commons.uitl.ConstantUtil;
import com.zhangshun.crm.commons.uitl.DateFormatUtil;
import com.zhangshun.crm.commons.uitl.UUIDUtil;
import com.zhangshun.crm.commons.vo.ReturnInfoVo;
import com.zhangshun.crm.settings.domain.User;
import com.zhangshun.crm.settings.service.UserService;
import com.zhangshun.crm.workbench.domain.Activity;
import com.zhangshun.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ActivityController {
    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @RequestMapping("/workbench/activity/selectUserList.do")
    public String selectUserList(HttpServletRequest request) {
        List<User> users = userService.queryAllUsers();
        request.setAttribute("users", users);
        return "workbench/activity/index";
    }

    @RequestMapping("/workbench/activity/saveCreateActivity.do")
    public @ResponseBody Object saveCreateActivity(Activity activity, HttpSession session) {
        //封装参数
        activity.setId(UUIDUtil.getUUID());
        activity.setCreateby(((User) session.getAttribute(ConstantUtil.SESSION_USER)).getId());
        activity.setCreatetime(DateFormatUtil.getDateFormat_FULL(new Date()));

        ReturnInfoVo returnInfoVo = new ReturnInfoVo();

        try {
            int res = activityService.saveCreateActivity(activity);
            if (res == 0) {
                returnInfoVo.setCode(ConstantUtil.FAIL_CODE);
                returnInfoVo.setMessage(ConstantUtil.INSERT_FAIL_MESSAGE);
            } else {
                returnInfoVo.setCode(ConstantUtil.SUCCESS_CODE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnInfoVo.setCode(ConstantUtil.FAIL_CODE);
            returnInfoVo.setMessage(ConstantUtil.INSERT_FAIL_MESSAGE);
        }
        return returnInfoVo;
    }

    @RequestMapping("/workbench/activity/selectActivityByConditionForPage.do")
    public @ResponseBody Object selectActivityByConditionForPage(String name, String owner, String startDate, String endDate, Integer pageNo, Integer pageSize) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        paramMap.put("owner", owner);
        paramMap.put("startDate", startDate);
        paramMap.put("endDate", endDate);
        paramMap.put("pageBegin", (pageNo - 1) * pageSize);
        paramMap.put("pageSize", pageSize);

//        根据条件和页数返回activity所有记录
        List<Activity> activityList = activityService.selectActivityByConditionForPage(paramMap);

//        根据根据条件和页数返回activity总记录的条数
        int totalRows = activityService.queryCountOfActivityByCondition(paramMap);

        Map<String, Object>  resMap = new HashMap<>();
        resMap.put("activityList", activityList);
        resMap.put("totalRows", totalRows);
        return resMap;
    }

    @RequestMapping("/workbench/activity/deleteActivityByList.do")
    public @ResponseBody Object deleteActivityByList(String[] ids) {
        ReturnInfoVo returnInfoVo = new ReturnInfoVo();

        try {
            int res = activityService.deleteActivityByList(ids);
            if (res > 0) {
                returnInfoVo.setCode(ConstantUtil.SUCCESS_CODE);
            } else {
                returnInfoVo.setCode(ConstantUtil.FAIL_CODE);
                returnInfoVo.setMessage("系统忙，请稍后再试...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnInfoVo.setCode(ConstantUtil.FAIL_CODE);
            returnInfoVo.setMessage("系统忙，请稍后再试...");
        }
        return returnInfoVo;
    }

    @RequestMapping("/workbench/activity/queryActivityById.do")
    public @ResponseBody Object queryActivityById(String id) {
        return activityService.queryActivityById(id);
    }

    @RequestMapping("/workbench/activity/saveEditActivity.do")
    public @ResponseBody Object saveEditActivity(Activity activity,HttpSession session) {
        ReturnInfoVo returnInfoVo = new ReturnInfoVo();

        activity.setEditby(((User) session.getAttribute("user")).getName());
        activity.setEdittime(DateFormatUtil.getDateFormat_FULL(new Date()));

        try {
            int res = activityService.saveEditActivity(activity);
            if (res != 0) {
                returnInfoVo.setCode(ConstantUtil.SUCCESS_CODE);
            } else {
                returnInfoVo.setCode(ConstantUtil.FAIL_CODE);
                returnInfoVo.setMessage("系统繁忙，请稍后再试...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnInfoVo.setCode(ConstantUtil.FAIL_CODE);
            returnInfoVo.setMessage("系统繁忙，请稍后再试...");
        }

        return returnInfoVo;
    }
}