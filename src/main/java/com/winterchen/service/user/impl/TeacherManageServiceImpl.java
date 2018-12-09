package com.winterchen.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winterchen.dao.TeacherManageDao;
import com.winterchen.model.dto.TeacherManageDto;
import com.winterchen.service.user.TeacherManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TeacherManageServiceImpl implements TeacherManageService {

    @Autowired
    private TeacherManageDao teacherManageDao;

    @Override
    public PageInfo<TeacherManageDto> select(int pageNum, int pageSize, String nickName) {
        PageHelper.startPage(pageNum,pageSize);
        TeacherManageDto query = new TeacherManageDto();

        query.setNickName(nickName);
        query.setNickName(nickName);
        List<TeacherManageDto> teacherManageDtoList =   teacherManageDao.select7(query);


        return new PageInfo(teacherManageDtoList);

    }
}
