package com.zhangshun.crm.workbench.service;

import com.zhangshun.crm.workbench.domain.ActivityRemark;

import java.util.List;
import java.util.Map;

public interface ActivityRemarkService {
    //根据市场活动id查询市场评论表信息
    List<ActivityRemark> queryActivityRemarkForDetailByActivityId(String activityId);

    //添加市场活动备注信息
    int saveCreateActivityRemark(ActivityRemark activityRemark);

    //通过id删除市场备注信息
    int deleteActivityRemarkById(String id);

    //更新市场备注信息
    int saveEditActivityRemark(Map<String, Object> remark);
}
