package com.zhangshun.crm.workbench.service.impl;

import com.zhangshun.crm.workbench.dao.ActivityMapper;
import com.zhangshun.crm.workbench.domain.Activity;
import com.zhangshun.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public int saveCreateActivity(Activity activity) {
        return activityMapper.insertActivity(activity);
    }

    @Override
    public List<Activity> selectActivityByConditionForPage(Map<String, Object> map) {
        return activityMapper.selectActivityByConditionForPage(map);
    }

    @Override
    public int queryCountOfActivityByCondition(Map<String, Object> map) {
        return activityMapper.queryCountOfActivityByCondition(map);
    }

    @Override
    public int deleteActivityByList(String[] ids) {
        return activityMapper.deleteActivityByList(ids);
    }

    @Override
    public Activity queryActivityById(String id) {
        return activityMapper.selectActivityById(id);
    }

    @Override
    public int saveEditActivity(Activity activity) {
        return activityMapper.saveEditActivity(activity);
    }


}
