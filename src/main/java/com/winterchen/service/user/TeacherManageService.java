package com.winterchen.service.user;

import com.github.pagehelper.PageInfo;
import com.winterchen.model.dto.TeacherManageDto;

public interface TeacherManageService {


    PageInfo<TeacherManageDto> select(int pageNum, int pageSize, String nickName);

}
