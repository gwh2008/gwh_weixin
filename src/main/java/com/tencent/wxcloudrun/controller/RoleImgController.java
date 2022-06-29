package com.tencent.wxcloudrun.controller;


import com.tencent.wxcloudrun.service.RoleImgService;
import com.tencent.wxcloudrun.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/roleImg")
public class RoleImgController {

    @Autowired
    private RoleImgService roleImgService;

    /**
     * 轮播图列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public JsonData findUserList(@RequestParam("pageNum") int pageNum,
                                 @RequestParam("pageSize") int pageSize) {
        HashMap hashMap =new HashMap();
        hashMap.put("data",roleImgService.roleImgFindList(pageNum,pageSize));
        return  JsonData.buildSuccess(hashMap);
    }

    /**
     * 轮播图新增
     * @param map
     * @return
     */
    @PostMapping("/insert")
    public JsonData insertUser(@RequestBody Map map) {
        map.put("createTime",new Date());
        map.put("rotaShow",1);
        int rows = roleImgService.roleImgInsert(map);
        if(rows == 1) {
            return JsonData.buildSuccess("","更新成功");
        } else {
            return JsonData.buildError("更新失败");
        }
    }

    /**
     * 轮播图隐藏
     */
    @GetMapping("updateRotaShow")
    public JsonData updateRotaShow(@RequestParam("id") int id,@RequestParam("status") boolean status) {
        int rows = roleImgService.updateRotaShow(id, status);
        if(rows == 1) {
            return JsonData.buildSuccess("更新成功");
        } else {
            return JsonData.buildError("更新失败");
        }
    }

    /**
     * 轮播图编辑
     */
    @PostMapping("/update")
    public JsonData update(@RequestBody Map map) {
        int rows = roleImgService.update(map);
        if(rows == 1) {
            return JsonData.buildSuccess("更新成功");
        } else {
            return JsonData.buildError("更新失败");
        }
    }

}
