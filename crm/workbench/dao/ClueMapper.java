package com.zhangshun.crm.workbench.dao;

import com.zhangshun.crm.workbench.domain.Clue;

import java.util.List;

public interface ClueMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Thu Apr 21 20:46:32 HKT 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Thu Apr 21 20:46:32 HKT 2022
     */
    int insert(Clue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Thu Apr 21 20:46:32 HKT 2022
     */
    int insertSelective(Clue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Thu Apr 21 20:46:32 HKT 2022
     */
    Clue selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Thu Apr 21 20:46:32 HKT 2022
     */
    int updateByPrimaryKeySelective(Clue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Thu Apr 21 20:46:32 HKT 2022
     */
    int updateByPrimaryKey(Clue record);

    /**
     * 添加线索
     * @param clue
     * @return
     */
    int insertClue(Clue clue);

    /**
     * 根据线索基本表的id查询线索基本信息
     * @param id
     * @return
     */
    Clue selectClueForDetailById(String id);
}