package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.model.User;
import com.tencent.wxcloudrun.service.UserService;
import com.tencent.wxcloudrun.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/user")
public class UserController {


    @Autowired
    private UserService userservice;

    /**
     * 查询用户列表接口,模糊查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public JsonData findUserList(@RequestParam("pageNum") int pageNum,
                                 @RequestParam("pageSize") int pageSize,
                                 @RequestParam("userName") String userName,
                                 @RequestParam("nickName") String nickName) {

        HashMap hashMap =new HashMap();
        hashMap.put("pageNum",pageNum);
        hashMap.put("pageSize",pageSize);
        hashMap.put("data",userservice.userFindList(pageNum,pageSize, userName,nickName));
        hashMap.put("total",userservice.userFindTotal());
        return  JsonData.buildSuccess(hashMap);
    }

    @PostMapping("/login")
    public JsonData loginUser(@RequestBody Map<String,Object> map) {
        String password = (String) map.get("password");
        String username = (String) map.get("username");
        String code = (String) map.get("code");
        // 判断是否有该用户
        Boolean userExist = userservice.usrFindByUsername(username);
        if(userExist == null) {
            return JsonData.buildError("该用户未注册");
        }
        User user = userservice.userLogin(password,username);
        if(user != null) {
            return  JsonData.buildSuccess(user,"用户登录成功");
        } else {
            return JsonData.buildError("用户或密码错误");
        }
    }

    @GetMapping("/delete")
    public JsonData deleteUser(@RequestParam("userId") String userId) {
        int rows = userservice.userDelete(userId);
        if(rows == 1) {
            return JsonData.buildSuccess("删除成功","删除成功");
        } else {
            return JsonData.buildError("删除失败");
        }
    }

    @PostMapping("/insert")
    public JsonData insertUser(@RequestBody Map map) {
        map.put("createTime",new Date());
        map.put("delFlag",0);
        map.put("status",1);
        map.put("password","123456");
        int rows = userservice.userInsert(map);
        if(rows == 1) {
            return JsonData.buildSuccess("新增成功");
        } else {
            return JsonData.buildError("新增失败");
        }
    }

    @PostMapping("/update")
    public JsonData updateUser(@RequestBody Map map) {
        int rows = userservice.userUpdate(map);
        if(rows == 1) {
            return JsonData.buildSuccess("","更新成功");
        } else {
            return JsonData.buildError("更新失败");
        }
    }

    @GetMapping("/updateState")
    public JsonData updateState(@RequestParam("userId") int userId,@RequestParam("status") boolean status) {
        int rows = userservice.updateState(userId, status);
        if(rows == 1) {
            return JsonData.buildSuccess("更新成功");
        } else {
            return JsonData.buildError("更新失败");
        }
    }

    /**
     * 修改用户密码并MD5加密
     * @param map
     * @return
     */
    @PostMapping("/updatePwd")
    public JsonData updatePwd(@RequestBody Map map) {
        int rows = userservice.userUpdatePwd(map);
        if(rows == 1) {
            return JsonData.buildSuccess("","更新成功");
        } else {
            return JsonData.buildError("更新失败");
        }
    }

}
