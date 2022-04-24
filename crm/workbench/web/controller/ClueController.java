package com.zhangshun.crm.workbench.web.controller;

import com.zhangshun.crm.commons.util.ConstantUtil;
import com.zhangshun.crm.commons.util.DateFormatUtil;
import com.zhangshun.crm.commons.util.UUIDUtil;
import com.zhangshun.crm.commons.vo.ReturnInfoVo;
import com.zhangshun.crm.settings.domain.DicValue;
import com.zhangshun.crm.settings.domain.User;
import com.zhangshun.crm.settings.service.UserService;
import com.zhangshun.crm.workbench.domain.Activity;
import com.zhangshun.crm.workbench.domain.Clue;
import com.zhangshun.crm.workbench.domain.ClueActivityRelation;
import com.zhangshun.crm.workbench.domain.ClueRemark;
import com.zhangshun.crm.workbench.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class ClueController {
    @Autowired
    private DicValueService dicValueService;

    @Autowired
    private UserService userService;

    @Autowired
    private ClueRemarkService clueRemarkService;

    @Autowired
    private ClueService clueService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ClueActivityRelationService clueActivityRelationService;

    @RequestMapping("/workbench/clue/queryDicValueByTypeCode.do")
    public String queryDicValueByTypeCode(HttpServletRequest request) {
        //查询线索的称呼
        List<DicValue> appellations = dicValueService.queryDicValueByTypeCode("appellation");
        //查询线索的状态
        List<DicValue> stages = dicValueService.queryDicValueByTypeCode("stage");
        //查询线索的来源
        List<DicValue> sources = dicValueService.queryDicValueByTypeCode("source");
        //查询所有者
        List<User> users = userService.queryAllUsers();

        request.setAttribute("appellations", appellations);
        request.setAttribute("stages", stages);
        request.setAttribute("sources", sources);
        request.setAttribute("users", users);
        return "workbench/clue/index";
    }

    @RequestMapping(value = "/workbench/clue/saveCreateClue.do", method = RequestMethod.POST)
    public @ResponseBody Object saveCreateClue(Clue clue, HttpSession session) {
        ReturnInfoVo returnInfoVo = new ReturnInfoVo();

        clue.setId(UUIDUtil.getUUID());
        clue.setCreatetime(DateFormatUtil.getDateFormat_FULL(new Date()));
        clue.setCreateby(((User) session.getAttribute(ConstantUtil.SESSION_USER)).getId());

        System.out.println(clue.getAddress());

        try {
            int res = clueService.saveCreateClue(clue);
            if (res > 0) {
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

    @RequestMapping(value = "/workbench/clue/queryClueForDetailById.do")
    public String queryClueForDetailById(String id, HttpServletRequest request) {
        Clue clue = clueService.queryClueForDetailById(id);
        List<ClueRemark> clueRemarks = clueRemarkService.queryClueRemarkForDetailByClueById(id);
        List<Activity> activities = activityService.queryActivityForDetailByClueId(id);

        request.setAttribute("clue", clue);
        request.setAttribute("clueRemarks", clueRemarks);
        request.setAttribute("activities", activities);
        return "workbench/clue/detail";
    }

    @RequestMapping(value = "/workbench/clue/queryActivityForDetailByNameClueId.do")
    public @ResponseBody List<Activity> queryActivityForDetailByNameClueId(String clueId, String activityName) {
        Map<String, Object> map = new HashMap<>();
        map.put("clueId", clueId);
        map.put("activityName", activityName);

        return activityService.queryActivityForDetailByNameClueId(map);
    }

    @RequestMapping("/workbench/clue/bundleRelation.do")
    public @ResponseBody Object bundleRelation(String[] activityIds, String clueId) {
        ReturnInfoVo returnInfoVo = new ReturnInfoVo();
        List<ClueActivityRelation> list = new ArrayList<>();
        ClueActivityRelation clueActivityRelation;

        for (String activityId:activityIds) {
            clueActivityRelation = new ClueActivityRelation();
            clueActivityRelation.setId(UUIDUtil.getUUID());
            clueActivityRelation.setActivityid(activityId);
            clueActivityRelation.setClueid(clueId);
            list.add(clueActivityRelation);
        }

        try {
            int res = clueActivityRelationService.saveClueActivityRelationByList(list);
            if (res > 0) {
                returnInfoVo.setCode(ConstantUtil.SUCCESS_CODE);
                List<Activity> activities = activityService.queryActivityForDetailByIds(activityIds);
                returnInfoVo.setReturnRes(activities);
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

    @RequestMapping("/workbench/clue/deleteClueActivityRelation.do")
    public @ResponseBody Object deleteClueActivityRelation(String activityId, String clueId) {
        ClueActivityRelation clueActivityRelation = new ClueActivityRelation();
        clueActivityRelation.setId(UUIDUtil.getUUID());
        clueActivityRelation.setClueid(clueId);
        clueActivityRelation.setActivityid(activityId);

        ReturnInfoVo returnInfoVo = new ReturnInfoVo();

        try {
            int res = clueActivityRelationService.deleteClueActivityRelationByClueIdActivityId(clueActivityRelation);
            if (res > 0) {
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

    @RequestMapping("/workbench/clue/convertTo.do")
    public String convertTo(String id,HttpServletRequest request) {
        Clue clue = clueService.queryClueForDetailById(id);
        List<DicValue> dicValues = dicValueService.queryDicValueByTypeCode("stage");
        request.setAttribute("clue", clue);
        request.setAttribute("dicValues", dicValues);
        return "workbench/clue/convert";
    }

    @RequestMapping("/workbench/clue/queryActivityConvertByNameClueId.do")
    public @ResponseBody Object queryActivityConvertByNameClueId(String activityName, String clueId) {
        Map<String, Object> map = new HashMap<>();
        map.put("activityName", activityName);
        map.put("clueId", clueId);

        return activityService.queryActivityConvertByNameClueId(map);
    }

    @RequestMapping("/workbench/clue/saveConvertClue.do")
    public @ResponseBody Object saveConvertClue(String clueId, String money, String tranName, String expectedDate, String stage, String activityId, String isTransaction, HttpSession session) {
        ReturnInfoVo returnInfoVo = new ReturnInfoVo();

        Map<String, String> map = new HashMap<>();
        map.put("clueId", clueId);
        map.put("money", money);
        map.put("tranName", tranName);
        map.put("expectedDate", expectedDate);
        map.put("stage", stage);
        map.put("activityId", activityId);
        map.put("isTransaction", isTransaction);
        map.put(ConstantUtil.SESSION_USER, ((User) session.getAttribute(ConstantUtil.SESSION_USER)).getId());

        try {
            clueService.saveConvertClue(map);
            returnInfoVo.setCode(ConstantUtil.SUCCESS_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            returnInfoVo.setCode(ConstantUtil.FAIL_CODE);
            returnInfoVo.setMessage("系统繁忙，请稍后再试...");
        }
        return returnInfoVo;
    }
}
