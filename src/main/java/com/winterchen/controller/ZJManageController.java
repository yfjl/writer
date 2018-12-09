package com.winterchen.controller;


import com.winterchen.service.user.TeacherManageService;
import com.winterchen.service.user.ZJManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/zj")
public class ZJManageController {


    @Autowired
    private ZJManageService zjManageService;


    @GetMapping("/select")
    public Map<String,Object> select(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize,

            @RequestParam(name = "nickName", required = false) String nickName

    ){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("data",zjManageService.select(pageNum,pageSize,nickName));
        return map;

    }

}
