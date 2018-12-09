package com.winterchen.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winterchen.dao.IntegralRecordDao;
import com.winterchen.model.IntegralRecordDomain;
import com.winterchen.service.user.IntegralRecordService;
import com.winterchen.util.utils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service(value = "IntegralRecordService")
public class IntegralRecordServiceImpl implements IntegralRecordService {

    @Autowired
    private IntegralRecordDao integralRecordDao;

    @Override
    public boolean insert(int type, int integral, String openid) {
        if(type == 1){
            IntegralRecordDomain query = new IntegralRecordDomain();
           query.setCreateTime(new Date());
           query.setIntegral(integral);
           query.setOpenid(openid);
           query.setType(type);
            int effectNum = integralRecordDao.insert(query);

            if(effectNum>0){
                return true;
            }else{
                throw new RuntimeException("提现失败！");
            }


        }
        return true;
    }

    @Override
    public PageInfo<IntegralRecordDomain> select(int pageNum, int pageSize, String openid) {
        PageHelper.startPage(pageNum,pageSize);
        IntegralRecordDomain query = new IntegralRecordDomain();

        query.setOpenid(openid);
        List<IntegralRecordDomain> zjManageDtoList =   integralRecordDao.select7(query);


        return new PageInfo(zjManageDtoList);

    }
}
