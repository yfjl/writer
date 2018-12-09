package com.winterchen.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winterchen.dao.TeacherManageDao;
import com.winterchen.dao.ZJManageDao;
import com.winterchen.model.dto.TeacherManageDto;
import com.winterchen.model.dto.ZJManageDto;
import com.winterchen.service.user.TeacherManageService;
import com.winterchen.service.user.ZJManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ZJManageServiceImpl implements ZJManageService {

    @Autowired
    private ZJManageDao zjManageDao;

    @Override
    public PageInfo<ZJManageDto> select(int pageNum, int pageSize, String nickName) {
        PageHelper.startPage(pageNum,pageSize);
        ZJManageDto query = new ZJManageDto();

        query.setNickName(nickName);
        List<ZJManageDto> zjManageDtoList =   zjManageDao.select7(query);


        return new PageInfo(zjManageDtoList);

    }
}
