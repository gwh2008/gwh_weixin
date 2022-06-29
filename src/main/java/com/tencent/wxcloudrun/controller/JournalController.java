package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.service.JournalService;
import com.tencent.wxcloudrun.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/journal")
public class JournalController {

    @Autowired
    JournalService journalService;

    @GetMapping("/list")
    public JsonData findUserList(@RequestParam("pageNum") int pageNum,
                                 @RequestParam("pageSize") int pageSize) {
        HashMap hashMap =new HashMap();
        hashMap.put("data",journalService.journaList(pageNum,pageSize));
        return  JsonData.buildSuccess(hashMap);
    }

    @PostMapping("/insert")
    public JsonData insertUser(@RequestBody Map map) {
        map.put("createTime",new Date());
        map.put("show",1);
        int rows = journalService.journalInsert(map);
        if(rows == 1) {
            return JsonData.buildSuccess("","更新成功");
        } else {
            return JsonData.buildError("更新失败");
        }
    }

    @PostMapping("/update")
    public JsonData update(@RequestBody Map map) {
        int rows = journalService.update(map);
        if(rows == 1) {
            return JsonData.buildSuccess("更新成功");
        } else {
            return JsonData.buildError("更新失败");
        }
    }

    @GetMapping("/updateShow")
    public JsonData updateTypeOneShow(@RequestParam("id") int id,@RequestParam("show") boolean show) {
        int rows = journalService.journalShow(id, show);
        if(rows == 1) {
            return JsonData.buildSuccess("更新成功");
        } else {
            return JsonData.buildError("更新失败");
        }
    }
}
