package com.zhangshun.crm.workbench.domain;

public class Transaction {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_tran.id
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_tran.owner
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    private String owner;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_tran.money
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    private String money;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_tran.name
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_tran.expectedDate
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    private String expecteddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_tran.customerId
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    private String customerid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_tran.stage
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    private String stage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_tran.type
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_tran.source
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    private String source;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_tran.activityId
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    private String activityid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_tran.contactsId
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    private String contactsid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_tran.createBy
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    private String createby;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_tran.createTime
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    private String createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_tran.editBy
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    private String editby;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_tran.editTime
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    private String edittime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_tran.description
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_tran.contactSummary
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    private String contactsummary;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_tran.nextContactTime
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    private String nextcontacttime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_tran.id
     *
     * @return the value of tbl_tran.id
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_tran.id
     *
     * @param id the value for tbl_tran.id
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_tran.owner
     *
     * @return the value of tbl_tran.owner
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public String getOwner() {
        return owner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_tran.owner
     *
     * @param owner the value for tbl_tran.owner
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_tran.money
     *
     * @return the value of tbl_tran.money
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public String getMoney() {
        return money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_tran.money
     *
     * @param money the value for tbl_tran.money
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public void setMoney(String money) {
        this.money = money == null ? null : money.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_tran.name
     *
     * @return the value of tbl_tran.name
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_tran.name
     *
     * @param name the value for tbl_tran.name
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_tran.expectedDate
     *
     * @return the value of tbl_tran.expectedDate
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public String getExpecteddate() {
        return expecteddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_tran.expectedDate
     *
     * @param expecteddate the value for tbl_tran.expectedDate
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public void setExpecteddate(String expecteddate) {
        this.expecteddate = expecteddate == null ? null : expecteddate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_tran.customerId
     *
     * @return the value of tbl_tran.customerId
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public String getCustomerid() {
        return customerid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_tran.customerId
     *
     * @param customerid the value for tbl_tran.customerId
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public void setCustomerid(String customerid) {
        this.customerid = customerid == null ? null : customerid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_tran.stage
     *
     * @return the value of tbl_tran.stage
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public String getStage() {
        return stage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_tran.stage
     *
     * @param stage the value for tbl_tran.stage
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public void setStage(String stage) {
        this.stage = stage == null ? null : stage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_tran.type
     *
     * @return the value of tbl_tran.type
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_tran.type
     *
     * @param type the value for tbl_tran.type
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_tran.source
     *
     * @return the value of tbl_tran.source
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public String getSource() {
        return source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_tran.source
     *
     * @param source the value for tbl_tran.source
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_tran.activityId
     *
     * @return the value of tbl_tran.activityId
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public String getActivityid() {
        return activityid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_tran.activityId
     *
     * @param activityid the value for tbl_tran.activityId
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public void setActivityid(String activityid) {
        this.activityid = activityid == null ? null : activityid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_tran.contactsId
     *
     * @return the value of tbl_tran.contactsId
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public String getContactsid() {
        return contactsid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_tran.contactsId
     *
     * @param contactsid the value for tbl_tran.contactsId
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public void setContactsid(String contactsid) {
        this.contactsid = contactsid == null ? null : contactsid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_tran.createBy
     *
     * @return the value of tbl_tran.createBy
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public String getCreateby() {
        return createby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_tran.createBy
     *
     * @param createby the value for tbl_tran.createBy
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_tran.createTime
     *
     * @return the value of tbl_tran.createTime
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public String getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_tran.createTime
     *
     * @param createtime the value for tbl_tran.createTime
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_tran.editBy
     *
     * @return the value of tbl_tran.editBy
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public String getEditby() {
        return editby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_tran.editBy
     *
     * @param editby the value for tbl_tran.editBy
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public void setEditby(String editby) {
        this.editby = editby == null ? null : editby.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_tran.editTime
     *
     * @return the value of tbl_tran.editTime
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public String getEdittime() {
        return edittime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_tran.editTime
     *
     * @param edittime the value for tbl_tran.editTime
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public void setEdittime(String edittime) {
        this.edittime = edittime == null ? null : edittime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_tran.description
     *
     * @return the value of tbl_tran.description
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_tran.description
     *
     * @param description the value for tbl_tran.description
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_tran.contactSummary
     *
     * @return the value of tbl_tran.contactSummary
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public String getContactsummary() {
        return contactsummary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_tran.contactSummary
     *
     * @param contactsummary the value for tbl_tran.contactSummary
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public void setContactsummary(String contactsummary) {
        this.contactsummary = contactsummary == null ? null : contactsummary.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_tran.nextContactTime
     *
     * @return the value of tbl_tran.nextContactTime
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public String getNextcontacttime() {
        return nextcontacttime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_tran.nextContactTime
     *
     * @param nextcontacttime the value for tbl_tran.nextContactTime
     *
     * @mbggenerated Sun Apr 24 16:14:29 HKT 2022
     */
    public void setNextcontacttime(String nextcontacttime) {
        this.nextcontacttime = nextcontacttime == null ? null : nextcontacttime.trim();
    }
}