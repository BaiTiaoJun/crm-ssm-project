package com.zhangshun.crm.workbench.web.controller;

import com.zhangshun.crm.commons.util.ConstantUtil;
import com.zhangshun.crm.commons.util.DateFormatUtil;
import com.zhangshun.crm.commons.util.UUIDUtil;
import com.zhangshun.crm.commons.vo.ReturnInfoVo;
import com.zhangshun.crm.settings.domain.User;
import com.zhangshun.crm.workbench.domain.ActivityRemark;
import com.zhangshun.crm.workbench.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ActivityRemarkController {
    @Autowired
    private ActivityRemarkService activityRemarkService;

    @RequestMapping("/workbench/activity/saveCreateActivityRemark.do")
    public @ResponseBody Object saveCreateActivityRemark(HttpSession session, String noteContent, String activityId) {
        //封装市场活动备注参数信息
        ActivityRemark activityRemark = new ActivityRemark();
        activityRemark.setId(UUIDUtil.getUUID());
        activityRemark.setNotecontent(noteContent);
        activityRemark.setCreatetime(DateFormatUtil.getDateFormat_FULL(new Date()));
        activityRemark.setCreateby(((User) session.getAttribute(ConstantUtil.SESSION_USER)).getId());
        activityRemark.setEditflag(ConstantUtil.EDIT_FLAG_NO_CODE);
        activityRemark.setActivityid(activityId);

        ReturnInfoVo returnInfoVo = new ReturnInfoVo();

        //调用市场活动备注服务
        try {
            int res = activityRemarkService.saveCreateActivityRemark(activityRemark);
            //判断是否获取到数据进行界面回显
            if (res > 0) {
                returnInfoVo.setCode(ConstantUtil.SUCCESS_CODE);
                returnInfoVo.setReturnRes(activityRemark);
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

    @RequestMapping("/workbench/activity/deleteActivityRemarkById.do")
    public @ResponseBody Object deleteActivityRemarkById(String id) {
        ReturnInfoVo returnInfoVo = new ReturnInfoVo();

        try {
            int res = activityRemarkService.deleteActivityRemarkById(id);
            if (res > 0) {
                returnInfoVo.setCode(ConstantUtil.SUCCESS_CODE);
            } else {
                returnInfoVo.setCode(ConstantUtil.FAIL_CODE);
                returnInfoVo.setMessage("系统繁忙，请稍后再试...");
            }
        } catch (Exception e) {
            returnInfoVo.setCode(ConstantUtil.FAIL_CODE);
            returnInfoVo.setMessage("系统繁忙，请稍后再试...");
        }

        return returnInfoVo;
    }

    @RequestMapping("/workbench/activity/saveEditActivityRemark.do")
    public @ResponseBody Object saveEditActivityRemark(String id, String noteContent, HttpSession session) {
        ReturnInfoVo returnInfoVo = new ReturnInfoVo();

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("noteContent", noteContent);
        map.put("editTime", DateFormatUtil.getDateFormat_FULL(new Date()));
        map.put("editBy", ((User) session.getAttribute(ConstantUtil.SESSION_USER)).getId());
        map.put("editFlag", ConstantUtil.EDIT_FLAG_YES_CODE);

        try {
            int res = activityRemarkService.saveEditActivityRemark(map);
            if (res > 0) {
                returnInfoVo.setCode(ConstantUtil.SUCCESS_CODE);
                returnInfoVo.setReturnRes(map);
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