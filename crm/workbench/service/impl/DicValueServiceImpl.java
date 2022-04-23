package com.zhangshun.crm.workbench.service.impl;

import com.zhangshun.crm.settings.dao.DicValueMapper;
import com.zhangshun.crm.settings.domain.DicValue;
import com.zhangshun.crm.workbench.service.DicValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("dicValueService")
public class DicValueServiceImpl implements DicValueService {
    @Autowired
    private DicValueMapper dicValueMapper;

    @Override
    public List<DicValue> queryDicValueByTypeCode(String typeCode) {
        return dicValueMapper.selectDicValueByTypeCode(typeCode);
    }
}
