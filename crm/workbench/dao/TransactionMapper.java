package com.zhangshun.crm.workbench.dao;

import com.zhangshun.crm.workbench.domain.Transaction;

public interface TransactionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    int insert(Transaction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    int insertSelective(Transaction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    Transaction selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    int updateByPrimaryKeySelective(Transaction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    int updateByPrimaryKey(Transaction record);

    /**
     * 如果需要创建交易,还要往交易表中添加一条记录
     * @param transaction
     * @return
     */
    int insertTransaction(Transaction transaction);
}