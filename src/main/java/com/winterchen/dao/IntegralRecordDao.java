package com.winterchen.dao;

import com.winterchen.model.IntegralRecordDomain;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IntegralRecordDao {

    int insert(IntegralRecordDomain integralRecordDomain);
    List<IntegralRecordDomain> select7(IntegralRecordDomain integralRecordDomain);
}
