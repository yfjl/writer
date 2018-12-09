package com.winterchen.service.user.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winterchen.dao.CapitalDetailDao;
import com.winterchen.dao.RoleDao;
import com.winterchen.dao.WithdrawDao;
import com.winterchen.model.CapitalDetail;
import com.winterchen.model.RoleDomain;
import com.winterchen.model.Withdraw;
import com.winterchen.service.user.WithdrawService;
import com.winterchen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class WithdrawServiceImpl implements WithdrawService
{


    @Autowired
    WithdrawDao withdrawDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    CapitalDetailDao capitalDetailDao;

    @Override
    public PageInfo<Withdraw> getWithdrawList(Map<String, Object> params) throws Exception {
        int pageNum = StringUtil.nullToInteger(params.get("pageNumber"));
        pageNum = pageNum == 0 ? 1 : pageNum;
        int pageSize = StringUtil.nullToInteger(params.get("pageSize"));
        pageSize = pageSize == 0 ? 10 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        List<Withdraw> list = withdrawDao.getListPrmMapRtnBean(params);
        PageInfo<Withdraw> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


    @Override
    public boolean addWithdraw(Withdraw withdraw) throws Exception {

        String openid = withdraw.getOpenid();
        RoleDomain roleObj =  roleDao.getInfo(openid);
        withdraw.setUserName(roleObj.getNickName());
        withdraw.setRole(roleObj.getRole());
        withdraw.setPhone(roleObj.getPhone());
        withdraw.setOpenid(roleObj.getOpenid());
        // 申请中
        withdraw.setStatus(0);
        withdraw.setUpdateTime(new Date());
        withdraw.setCreateTime(new Date());
         boolean effNum =  withdrawDao.getInsert(withdraw);
         if(effNum == true){
           int num =  roleDao.updateMoney(openid);
           if(num>0){
               CapitalDetail capitalDetail = new CapitalDetail();
               capitalDetail.setCashPoint(0);
               // 支出
               capitalDetail.setInOut(1);
               if("3".equals(withdraw.getRole())){
                   capitalDetail.setTeacherDivide(withdraw.getAmount());
                   capitalDetail.setOwnerTeacher(withdraw.getOpenid());
                   capitalDetail.setOwnerTeacherName(withdraw.getUserName());
               } else if("4".equals(withdraw.getRole())) {
                   capitalDetail.setInspectorDivide(withdraw.getAmount());
                   capitalDetail.setOwnerInspector(withdraw.getOpenid());
                   capitalDetail.setOwnerInspectorName(withdraw.getUserName());
               }
               capitalDetail.setRemarks("提现");
               //capitalDetail.setStatus(1);
               capitalDetail.setCreateTime(new Date());
               capitalDetailDao.getInsert(capitalDetail);
               return true;
           }
         }
        return true;
    }

    @Override
    public boolean updateWithdraw(Withdraw wd) throws Exception {
        Integer id = wd.getId();
        Integer event = wd.getStatus();
        if(null == id || event == null){
            return false;
        }
        Withdraw withdraw = withdrawDao.getBeanById(id);
        if(null == withdraw || 0 != withdraw.getStatus()){
            //重复提交
            return false;
        }
        // 提现驳回
        if(2 == event){
            // 资金退回
            roleDao.updateBlance(withdraw.getOpenid(),withdraw.getAmount(),0);
            CapitalDetail capitalDetail = new CapitalDetail();
            capitalDetail.setCashPoint(0);
            capitalDetail.setInOut(0);
            if("3".equals(withdraw.getRole())){
                capitalDetail.setTeacherDivide(withdraw.getAmount());
                capitalDetail.setOwnerTeacher(withdraw.getOpenid());
                capitalDetail.setOwnerTeacherName(withdraw.getUserName());
            } else if("4".equals(withdraw.getRole())){
                capitalDetail.setInspectorDivide(withdraw.getAmount());
                capitalDetail.setOwnerInspector(withdraw.getOpenid());
                capitalDetail.setOwnerInspectorName(withdraw.getUserName());
            }
            capitalDetail.setRemarks("提现驳回，资金退回");
            capitalDetail.setCreateTime(new Date());
            capitalDetailDao.getInsert(capitalDetail);
        }
        wd.setUpdateTime(new Date());
        withdrawDao.getUpdate(wd);
        return true;
    }

    @Override
    public Object findWithdraw(Integer id) throws Exception {
        return withdrawDao.getBeanById(id);
    }
}
