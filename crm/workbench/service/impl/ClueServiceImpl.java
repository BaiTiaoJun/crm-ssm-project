package com.zhangshun.crm.workbench.service.impl;

import com.zhangshun.crm.commons.util.ConstantUtil;
import com.zhangshun.crm.commons.util.DateFormatUtil;
import com.zhangshun.crm.commons.util.UUIDUtil;
import com.zhangshun.crm.workbench.dao.*;
import com.zhangshun.crm.workbench.domain.*;
import com.zhangshun.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("clueService")
public class ClueServiceImpl implements ClueService {
    @Autowired
    private ClueMapper clueMapper;

    @Autowired
    private ClueRemarkMapper clueRemarkMapper;

    @Autowired
    private ClueActivityRelationMapper clueActivityRelationMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerRemarkMapper customerRemarkMapper;

    @Autowired
    private ContactsMapper contactsMapper;

    @Autowired
    private ContactsRemarkMapper contactsRemarkMapper;

    @Autowired
    private ContactsActivityRelationMapper contactsActivityRelationMapper;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private TransactionRemarkMapper transactionRemarkMapper;

    @Override
    public int saveCreateClue(Clue clue) {
        return clueMapper.insertClue(clue);
    }

    @Override
    public Clue queryClueForDetailById(String id) {
        return clueMapper.selectClueForDetailById(id);
    }

    @Override
    public void saveConvertClue(Map<String, String> map) {
        //获取当前用户
        String currentUser = map.get(ConstantUtil.SESSION_USER);
        //获取来源
        String activityId = map.get("activityId");
        //获取isTransaction是否是true
        String isTransaction = map.get("isTransaction");

        //根据clueId查询线索信息
        String clueId = map.get("clueId");
        Clue clue = clueMapper.selectClueById(clueId);

        //把线索中的有关公司信息转换到客户表中
        Customer customer = new Customer();
        customer.setId(UUIDUtil.getUUID());
        customer.setOwner(currentUser);
        customer.setName(clue.getCompany());
        customer.setWebsite(clue.getWebsite());
        customer.setPhone(clue.getPhone());
        customer.setCreateby(currentUser);
        customer.setCreatetime(DateFormatUtil.getDateFormat_FULL(new Date()));
        customer.setContactsummary(clue.getContactsummary());
        customer.setNextcontacttime(clue.getNextcontacttime());
        customer.setDescription(clue.getDescription());
        customer.setAddress(clue.getAddress());

        customerMapper.insertCustomer(customer);

        //把线索中的有关个人信息转换到联系人表中
        Contacts contacts = new Contacts();
        contacts.setId(UUIDUtil.getUUID());
        contacts.setOwner(currentUser);
        contacts.setSource(activityId);
        contacts.setCustomerid(customer.getId());
        contacts.setFullname(clue.getFullname());
        contacts.setAppellation(clue.getAppellation());
        contacts.setEmail(clue.getEmail());
        contacts.setMphone(clue.getMphone());
        contacts.setJob(clue.getJob());
        contacts.setCreateby(currentUser);
        contacts.setCreatetime(DateFormatUtil.getDateFormat_FULL(new Date()));
        contacts.setDescription(clue.getDescription());
        contacts.setContactsummary(clue.getContactsummary());
        contacts.setAddress(clue.getAddress());

        contactsMapper.insertContacts(contacts);

        //根据clueid查询线索下所有的备注
        List<ClueRemark> clueRemarks = clueRemarkMapper.selectClueRemarksById(clueId);

        List<CustomerRemark> customerRemarks = new ArrayList<>();
        List<ContactsRemark> contactsRemarks = new ArrayList<>();
        CustomerRemark customerRemark;
        ContactsRemark contactsRemark;
        for (ClueRemark clueRemark:clueRemarks) {
            //把该线索下所有的备注转换到客户备注表中
            customerRemark = new CustomerRemark();
            customerRemark.setId(UUIDUtil.getUUID());
            customerRemark.setNotecontent(clueRemark.getNotecontent());
            customerRemark.setCreateby(currentUser);
            customerRemark.setCreatetime(DateFormatUtil.getDateFormat_FULL(new Date()));
            customerRemark.setEditby(clueRemark.getEditby());
            customerRemark.setEdittime(clueRemark.getEdittime());
            customerRemark.setEditflag(clueRemark.getEditflag());
            customerRemark.setCustomerid(customer.getId());
            customerRemarks.add(customerRemark);
            //把该线索下所有的备注转换到联系人备注表中
            contactsRemark = new ContactsRemark();
            contactsRemark.setId(UUIDUtil.getUUID());
            contactsRemark.setNotecontent(clueRemark.getNotecontent());
            contactsRemark.setCreateby(currentUser);
            contactsRemark.setCreatetime(DateFormatUtil.getDateFormat_FULL(new Date()));
            contactsRemark.setEditby(clueRemark.getEditby());
            contactsRemark.setEdittime(clueRemark.getEdittime());
            contactsRemark.setEditflag(clueRemark.getEditflag());
            contactsRemark.setContactsid(contacts.getId());
            contactsRemarks.add(contactsRemark);
        }

        customerRemarkMapper.insertCustomerRemarkByClueRemarkList(customerRemarks);
        contactsRemarkMapper.insertContactsRemarkByClueRemarkList(contactsRemarks);

        //根据clueId查询该线索和市场活动的关联关系
        List<ClueActivityRelation> clueActivityRelations = clueActivityRelationMapper.selectClueActivityRelationByClueId(clueId);

        //把该线索和市场活动关联关系转换到联系人和市场活动的关联关系表中
        List<ContactsActivityRelation>  contactsActivityRelations = new ArrayList<>();
        ContactsActivityRelation contactsActivityRelation;
        for (ClueActivityRelation clueActivityRelation:clueActivityRelations) {
            contactsActivityRelation = new ContactsActivityRelation();
            contactsActivityRelation.setId(UUIDUtil.getUUID());
            contactsActivityRelation.setActivityid(clueActivityRelation.getActivityid());
            contactsActivityRelation.setContactsid(contacts.getId());
            contactsActivityRelations.add(contactsActivityRelation);
        }

        contactsActivityRelationMapper.insertContactsActivityRelationByClueActivityRelationList(contactsActivityRelations);

        //如果需要创建交易,还要往交易表中添加一条记录
        if ("true".equals(isTransaction)) {
            Transaction transaction = new Transaction();
            transaction.setId(UUIDUtil.getUUID());
            transaction.setOwner(currentUser);
            transaction.setMoney(map.get("money"));
            transaction.setName(map.get("tranName"));
            transaction.setExpecteddate(map.get("expectedDate"));
            transaction.setCustomerid(customer.getId());
            transaction.setStage(map.get("stage"));
            transaction.setSource(activityId);
            transaction.setCreateby(currentUser);
            transaction.setCreatetime(DateFormatUtil.getDateFormat_FULL(new Date()));

            transactionMapper.insertTransaction(transaction);

            //如果需要创建交易,还要把线索的备注信息转换到交易备注表中
            TransactionRemark transactionRemark;
            List<TransactionRemark> transactionRemarks = new ArrayList<>();
            for (ClueRemark clueRemark:clueRemarks) {
                transactionRemark = new TransactionRemark();
                transactionRemark.setId(UUIDUtil.getUUID());
                transactionRemark.setNotecontent(clueRemark.getNotecontent());
                transactionRemark.setCreateby(currentUser);
                transactionRemark.setCreatetime(DateFormatUtil.getDateFormat_FULL(new Date()));
                transactionRemark.setEditby(clueRemark.getEditby());
                transactionRemark.setEdittime(clueRemark.getEdittime());
                transactionRemark.setEditflag(clueRemark.getEditflag());
                transactionRemark.setTranid(transaction.getId());
                transactionRemarks.add(transactionRemark);
            }
            transactionRemarkMapper.insertTransactionRemarkByClueRemark(transactionRemarks);
        }

        //删除线索
        clueMapper.deleteClueById(clueId);
        //删除线索的备注
        clueRemarkMapper.deleteClueRemarkMapperByClueId(clueId);
        //删除线索和市场活动的关联关系
        clueActivityRelationMapper.deleteClueActivityRelationMapperByClueId(clueId);
    }
}
