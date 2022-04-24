package com.zhangshun.crm.workbench.service;

import com.zhangshun.crm.workbench.domain.Clue;

import java.util.Map;

public interface ClueService {
    //添加线索
    int saveCreateClue(Clue clue);

    //根据线索基本表的id查询线索基本信息
    Clue queryClueForDetailById(String id);

    //把线索相关信息进行转换
    void saveConvertClue(Map<String, String> map);
}
