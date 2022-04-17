package com.zhangshun.crm.workbench.service;

import com.zhangshun.crm.workbench.domain.ActivityRemark;

import java.util.List;

public interface ActivityRemarkService {
    //根据市场活动id查询市场评论表信息
    List<ActivityRemark> queryActivityRemarkForDetailByActivityId(String activityId);
}
