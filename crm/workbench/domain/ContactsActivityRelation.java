package com.zhangshun.crm.workbench.domain;

public class ContactsActivityRelation {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_contacts_activity_relation.id
     *
     * @mbggenerated Sun Apr 24 15:36:15 HKT 2022
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_contacts_activity_relation.contactsId
     *
     * @mbggenerated Sun Apr 24 15:36:15 HKT 2022
     */
    private String contactsid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_contacts_activity_relation.activityId
     *
     * @mbggenerated Sun Apr 24 15:36:15 HKT 2022
     */
    private String activityid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_contacts_activity_relation.id
     *
     * @return the value of tbl_contacts_activity_relation.id
     *
     * @mbggenerated Sun Apr 24 15:36:15 HKT 2022
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_contacts_activity_relation.id
     *
     * @param id the value for tbl_contacts_activity_relation.id
     *
     * @mbggenerated Sun Apr 24 15:36:15 HKT 2022
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_contacts_activity_relation.contactsId
     *
     * @return the value of tbl_contacts_activity_relation.contactsId
     *
     * @mbggenerated Sun Apr 24 15:36:15 HKT 2022
     */
    public String getContactsid() {
        return contactsid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_contacts_activity_relation.contactsId
     *
     * @param contactsid the value for tbl_contacts_activity_relation.contactsId
     *
     * @mbggenerated Sun Apr 24 15:36:15 HKT 2022
     */
    public void setContactsid(String contactsid) {
        this.contactsid = contactsid == null ? null : contactsid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_contacts_activity_relation.activityId
     *
     * @return the value of tbl_contacts_activity_relation.activityId
     *
     * @mbggenerated Sun Apr 24 15:36:15 HKT 2022
     */
    public String getActivityid() {
        return activityid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_contacts_activity_relation.activityId
     *
     * @param activityid the value for tbl_contacts_activity_relation.activityId
     *
     * @mbggenerated Sun Apr 24 15:36:15 HKT 2022
     */
    public void setActivityid(String activityid) {
        this.activityid = activityid == null ? null : activityid.trim();
    }
}