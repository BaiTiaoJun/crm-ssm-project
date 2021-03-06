package com.zhangshun.crm.workbench.dao;

import com.zhangshun.crm.workbench.domain.ClueRemark;
import com.zhangshun.crm.workbench.domain.ContactsRemark;

import java.util.List;

public interface ContactsRemarkMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_remark
     *
     * @mbggenerated Sun Apr 24 14:51:11 HKT 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_remark
     *
     * @mbggenerated Sun Apr 24 14:51:11 HKT 2022
     */
    int insert(ContactsRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_remark
     *
     * @mbggenerated Sun Apr 24 14:51:11 HKT 2022
     */
    int insertSelective(ContactsRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_remark
     *
     * @mbggenerated Sun Apr 24 14:51:11 HKT 2022
     */
    ContactsRemark selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_remark
     *
     * @mbggenerated Sun Apr 24 14:51:11 HKT 2022
     */
    int updateByPrimaryKeySelective(ContactsRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_remark
     *
     * @mbggenerated Sun Apr 24 14:51:11 HKT 2022
     */
    int updateByPrimaryKey(ContactsRemark record);

    /**
     * 把该线索下所有的备注转换到联系人备注表中
     * @param clueRemark
     * @return
     */
    int insertContactsRemarkByClueRemarkList(List<ContactsRemark> clueRemark);
}