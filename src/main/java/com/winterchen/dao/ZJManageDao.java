package com.winterchen.dao;

import com.winterchen.model.dto.TeacherManageDto;
import com.winterchen.model.dto.ZJManageDto;

import java.util.List;

public interface ZJManageDao {

    List<ZJManageDto> select7(ZJManageDto ZJManageDto);
}
