package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.service.GoodsService;
import com.tencent.wxcloudrun.utils.JsonData;
import com.tencent.wxcloudrun.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/list")
    public JsonData findUserList(@RequestParam("pageNum") int pageNum,
                                 @RequestParam("pageSize") int pageSize
                                 ) {

        HashMap hashMap =new HashMap();
        hashMap.put("data",goodsService.goodsFindList(pageNum,pageSize));
        return  JsonData.buildSuccess(hashMap);
    }

    /**
     * 新增
     */
    @PostMapping("/insert")
    public JsonData insert(@RequestBody Map map) {
        map.put("goodsShow",1);
        map.put("recommend",1);
        int rows = goodsService.insert(map);
        if(rows == 1) {
            return JsonData.buildSuccess("更新成功");
        } else {
            return JsonData.buildError("更新失败");
        }
    }

    /**
     * 编辑
     * @param map
     * @return
     */
    @PostMapping("/update")
    public JsonData update(@RequestBody Map map) {
        int rows = goodsService.update(map);
        if(rows == 1) {
            return JsonData.buildSuccess("更新成功");
       } else {
            return JsonData.buildError("更新失败"); }
    }

    /**
     * 商品显示
     * @param id
     * @return
     */
    @GetMapping("/updateShow")
    public JsonData updateRotaShow(@RequestParam("id") int id,@RequestParam("goodsShow") boolean goodsShow) {
        int rows = goodsService.updateShow(id, goodsShow);
        if(rows == 1) {
            return JsonData.buildSuccess("更新成功");
        } else {
            return JsonData.buildError("更新失败");
        }
    }

    /**
     * 商品推荐
     * @param id
     * @param recommend
     * @return
     */
    @GetMapping("/updateRecommend")
    public JsonData updateRecommend(@RequestParam("id") int id,@RequestParam("recommend") boolean recommend) {
        int rows = goodsService.updateRecommend(id, recommend);
        if(rows == 1) {
            return JsonData.buildSuccess("更新成功");
        } else {
            return JsonData.buildError("更新失败");
        }
    }




}
