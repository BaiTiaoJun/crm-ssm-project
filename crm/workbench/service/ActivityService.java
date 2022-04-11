package com.zhangshun.crm.workbench.service;

import com.zhangshun.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityService {
//    添加市场活动信息
    int saveCreateActivity(Activity activity);

//    根据查询条件和页数返回查询的信息
    List<Activity> selectActivityByConditionForPage(Map<String, Object> map);

//    根据查询条件返回查询信息的总记录条数
    int queryCountOfActivityByCondition(Map<String,  Object> map);

    //根据id数组删除市场活动信息
    int deleteActivityByList(String[] ids);

    //根据id查询市场活动信息
    Activity queryActivityById(String id);

    //更新修改后的市场活动信息
    int saveEditActivity(Activity activity);
}
