package com.xx.controller;


import com.xx.service.DutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class DutyController {

    @Autowired
    private DutyService dutyService;

    //上传文件
    @RequestMapping
    public void insertExcel(@RequestParam("file")MultipartFile file){
        dutyService.readExcel(file);

    }

}
