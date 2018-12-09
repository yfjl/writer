package com.winterchen.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winterchen.dao.DLManageDao;
import com.winterchen.model.dto.DLManageDto;
import com.winterchen.service.user.DLManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DLManageServiceImpl implements DLManageService {

    @Autowired
    private DLManageDao dlManageDao;
    @Override
    public PageInfo<DLManageDto> select(int pageNum, int pageSize, String nickName) {
        PageHelper.startPage(pageNum,pageSize);
        DLManageDto query = new DLManageDto();

        query.setNickName(nickName);
        List<DLManageDto> zjManageDtoList =   dlManageDao.select7(query);


        return new PageInfo(zjManageDtoList);
    }
}
