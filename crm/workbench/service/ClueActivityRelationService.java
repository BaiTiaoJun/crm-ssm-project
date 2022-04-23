package com.zhangshun.crm.workbench.service;

import com.zhangshun.crm.workbench.domain.ClueActivityRelation;

import java.util.List;

public interface ClueActivityRelationService {
    int saveClueActivityRelationByList(List<ClueActivityRelation> list);
}
