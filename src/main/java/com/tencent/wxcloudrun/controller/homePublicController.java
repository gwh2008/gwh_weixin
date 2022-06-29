package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.service.GoodsService;
import com.tencent.wxcloudrun.service.RoleImgService;
import com.tencent.wxcloudrun.service.TypeOneService;
import com.tencent.wxcloudrun.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/public")
public class homePublicController {
    @Autowired
    private RoleImgService roleImgService;

    @Autowired
    private TypeOneService typeOneService;

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/roleList")
    public JsonData findUserList() {
        return  JsonData.buildSuccess(roleImgService.publicRole());
    }

    @GetMapping("/typeName")
    public JsonData typeName() {
        return  JsonData.buildSuccess(typeOneService.publicTypeName());
    }

    @GetMapping("/goodFirstImg")
    public JsonData updateRotaShow(@RequestParam("typeId") int typeId) {
        return  JsonData.buildSuccess(goodsService.goodFirstImg(typeId));
    }

    @GetMapping("/recommend")
    public JsonData updateRotaShow() {
        return  JsonData.buildSuccess(goodsService.recommend());
    }

    @GetMapping("/goodDetail")
    public JsonData goodDetail(@RequestParam("id") int id) {
        return  JsonData.buildSuccess(goodsService.goodDetail(id));
    }
}
