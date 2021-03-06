package com.zhangshun.crm.workbench.dao;

import com.zhangshun.crm.workbench.domain.Clue;
import com.zhangshun.crm.workbench.domain.Customer;

public interface CustomerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbggenerated Sun Apr 24 13:01:29 HKT 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbggenerated Sun Apr 24 13:01:29 HKT 2022
     */
    int insert(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbggenerated Sun Apr 24 13:01:29 HKT 2022
     */
    int insertSelective(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbggenerated Sun Apr 24 13:01:29 HKT 2022
     */
    Customer selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbggenerated Sun Apr 24 13:01:29 HKT 2022
     */
    int updateByPrimaryKeySelective(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbggenerated Sun Apr 24 13:01:29 HKT 2022
     */
    int updateByPrimaryKey(Customer record);

    int insertCustomer(Customer customer);
}