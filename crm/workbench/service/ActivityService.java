package com.zhangshun.crm.workbench.service;

import com.zhangshun.crm.workbench.domain.Activity;
import com.zhangshun.crm.workbench.domain.ActivityRemark;

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
    int updateEditActivity(Activity activity);

    //根据市场活动id查看一条或者多条活动信息
    List<Activity> queryAllActivities();

    //根据市场活动id查看一条或者多条活动信息
    List<Activity> queryActivitiesById(String[] ids);

    //批量保存excel表中创建的市场活动
    int saveCreateActivityByList(List<Activity> activityList);

    //根据id查询市场活动明细
    Activity queryActivityForDetailById(String id);

    //根据线索基本表的id查询市场所关联的市场活动信息
    List<Activity> queryActivityForDetailByClueId(String clueId);

    //根据线索id和市场名称，模糊查询没有和线索关联的市场活动
    List<Activity> queryActivityForDetailByNameClueId(Map<String, Object> map);

    //添加完关联信息后,根据已和线索关联的市场活动id，查询市场活动
    List<Activity> queryActivityForDetailByIds(String[] ids);

    //根据活动名称和线索id查询已和线索关联的活动信息
    List<Activity> queryActivityConvertByNameClueId(Map<String, Object> map);
}
