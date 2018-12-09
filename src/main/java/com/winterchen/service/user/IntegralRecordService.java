package com.winterchen.service.user;

import com.github.pagehelper.PageInfo;
import com.winterchen.model.IntegralRecordDomain;

public interface IntegralRecordService {


    boolean insert(int type,int integral,String openid);



    PageInfo<IntegralRecordDomain> select(int pageNum, int pageSize, String openid);
}
