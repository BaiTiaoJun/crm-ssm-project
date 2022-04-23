package com.zhangshun.crm.workbench.service.impl;

import com.zhangshun.crm.workbench.dao.ClueRemarkMapper;
import com.zhangshun.crm.workbench.domain.ClueRemark;
import com.zhangshun.crm.workbench.service.ClueRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("clueRemarkService")
public class ClueRemarkServiceImpl implements ClueRemarkService {

    @Autowired
    private ClueRemarkMapper clueRemarkMapper;

    @Override
    public List<ClueRemark> queryClueRemarkForDetailByClueById(String clueId) {
        return clueRemarkMapper.selectClueRemarkForDetailByClueId(clueId);
    }
}
