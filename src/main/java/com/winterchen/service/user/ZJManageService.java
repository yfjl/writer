package com.winterchen.service.user;

import com.github.pagehelper.PageInfo;
import com.winterchen.model.dto.TeacherManageDto;
import com.winterchen.model.dto.ZJManageDto;

public interface ZJManageService {


    PageInfo<ZJManageDto> select(int pageNum, int pageSize, String nickName);

}
