package com.zhangshun.crm.workbench.service.impl;

import com.zhangshun.crm.workbench.dao.ActivityRemarkMapper;
import com.zhangshun.crm.workbench.domain.ActivityRemark;
import com.zhangshun.crm.workbench.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("activityRemarkService")
public class ActivityRemarkServiceImpl implements ActivityRemarkService {
    @Autowired
    private ActivityRemarkMapper activityRemarkMapper;

    @Override
    public List<ActivityRemark> queryActivityRemarkForDetailByActivityId(String activityId) {
        return activityRemarkMapper.selectActivityRemarkForDetailByActivityId(activityId);
    }

    @Override
    public int saveCreateActivityRemark(ActivityRemark activityRemark) {
        return activityRemarkMapper.insertActivityRemark(activityRemark);
    }

    @Override
    public int deleteActivityRemarkById(String id) {
        return activityRemarkMapper.deleteActivityRemarkById(id);
    }

    @Override
    public int saveEditActivityRemark(Map<String, Object> remark) {
        return activityRemarkMapper.updateActivityRemark(remark);
    }
}
