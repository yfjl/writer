package com.winterchen.dao;

import com.winterchen.model.dto.TeacherManageDto;

import java.util.List;

public interface TeacherManageDao {

    List<TeacherManageDto> select7(TeacherManageDto teacherManageDto);
}
