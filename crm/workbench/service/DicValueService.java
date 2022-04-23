package com.zhangshun.crm.workbench.service;

import com.zhangshun.crm.settings.domain.DicValue;

import java.util.List;

public interface DicValueService {
    //根据字典值类型的查询字典列表值
    List<DicValue> queryDicValueByTypeCode(String typeCode);
}
