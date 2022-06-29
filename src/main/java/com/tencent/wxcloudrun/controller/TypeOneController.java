package com.tencent.wxcloudrun.controller;


import com.tencent.wxcloudrun.service.TypeOneService;
import com.tencent.wxcloudrun.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/typeOne")
public class TypeOneController {

    @Autowired
    private TypeOneService typeOneService;

    /**
     * 查询一级分类列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public JsonData findUserList(@RequestParam("pageNum") int pageNum,
                                 @RequestParam("pageSize") int pageSize) {

        HashMap hashMap =new HashMap();
        hashMap.put("data",typeOneService.typeOneFindList(pageNum,pageSize));
        return  JsonData.buildSuccess(hashMap);
    }
    /**
     * 查询typeName 和 id
     */
    @GetMapping("/typeName")
    public JsonData typeName() {
        return  JsonData.buildSuccess(typeOneService.typeName());
    }
    /**
     * 插入分类
     * @param map
     * @return
     */
    @PostMapping("/insert")
    public JsonData insertUser(@RequestBody Map map) {
        map.put("createTime",new Date());
        map.put("show",1);
        int rows = typeOneService.typeOneInsert(map);
        if(rows == 1) {
            return JsonData.buildSuccess("","更新成功");
        } else {
            return JsonData.buildError("更新失败");
        }
    }

    /**
     * 编辑分类
     * @return
     */
    @PostMapping("/update")
    public JsonData update(@RequestBody Map map) {
        int rows = typeOneService.update(map);
        if(rows == 1) {
            return JsonData.buildSuccess("更新成功");
        } else {
            return JsonData.buildError("更新失败");
        }
    }

    /**
     * 显示隐藏
     */
    @GetMapping("updateTypeOneShow")
    public JsonData updateTypeOneShow(@RequestParam("id") int id,@RequestParam("show") boolean show) {
        int rows = typeOneService.updateTypeOneShow(id, show);
        if(rows == 1) {
            return JsonData.buildSuccess("更新成功");
        } else {
            return JsonData.buildError("更新失败");
        }
    }

}
