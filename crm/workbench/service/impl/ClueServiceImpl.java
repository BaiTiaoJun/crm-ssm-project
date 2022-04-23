package com.zhangshun.crm.workbench.service.impl;

import com.zhangshun.crm.workbench.dao.ClueMapper;
import com.zhangshun.crm.workbench.domain.Clue;
import com.zhangshun.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clueService")
public class ClueServiceImpl implements ClueService {
    @Autowired
    private ClueMapper clueMapper;

    @Override
    public int saveCreateClue(Clue clue) {
        return clueMapper.insertClue(clue);
    }

    @Override
    public Clue queryClueForDetailById(String id) {
        return clueMapper.selectClueForDetailById(id);
    }
}
