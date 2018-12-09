package com.winterchen.controller;


import com.winterchen.service.user.IntegralRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/record")
public class IntegralRecordController {

    @Autowired
    private IntegralRecordService integralRecordService;


    @GetMapping("/outInter")
    public Map<String,Object> outInter(int type,int integral,String openid){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("success",integralRecordService.insert(type,integral,openid));

        return  map;
    }


    @GetMapping("/select")
    public Map<String,Object> select(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize,

            String openid

    ){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("data",integralRecordService.select(pageNum,pageSize,openid));
        return map;

    }
}
