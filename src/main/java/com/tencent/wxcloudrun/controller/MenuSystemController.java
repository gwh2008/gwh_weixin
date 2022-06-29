package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.service.MenuSystemService;
import com.tencent.wxcloudrun.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/menu")
public class MenuSystemController {

    @Autowired
    private MenuSystemService menuSystemService;

    @GetMapping("/getRouter")
    public JsonData findRouter() {
        return JsonData.buildSuccess(menuSystemService.findRouter());
    }
}
