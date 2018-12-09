package com.winterchen.service.user;

import com.github.pagehelper.PageInfo;
import com.winterchen.model.dto.DLManageDto;

public interface DLManageService {
    PageInfo<DLManageDto> select(int pageNum, int pageSize, String nickName);

}
