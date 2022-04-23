package com.zhangshun.crm.workbench.service;

import com.zhangshun.crm.workbench.domain.ClueRemark;

import java.util.List;

public interface ClueRemarkService {
    List<ClueRemark> queryClueRemarkForDetailByClueById(String clueId);
}
