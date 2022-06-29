package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.model.User;
import com.tencent.wxcloudrun.dao.UserMapper;
import com.tencent.wxcloudrun.service.UserService;
import com.tencent.wxcloudrun.utils.CommonUtils;
import com.tencent.wxcloudrun.utils.JWTUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<User> userFindList(int pageNum, int pageSize, String userName, String nickName) {
//        int pageMin = (pageNum-1) * pageSize;
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.findUserList(userName, nickName);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    @Override
    public User userLogin(String password, String username) {
        password = CommonUtils.MD5(password);
        User user = userMapper.loginUser(password, username);
        if(user == null){
            return null;

        }else {
            String token = JWTUtils.geneJsonWebToken(user);
            user.setToken(token);
            return user;
        }
    }

    @Override
    public Boolean usrFindByUsername(String username) {
        if (userMapper.findUserByUserName(username) != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object userFindTotal() {
        return userMapper.findUserTotal();
    }

    @Override
    public int userDelete(String userId) {
        return userMapper.deleteUser(userId);
    }

    @Override
    public int userInsert(Map map) {
        return userMapper.insertUser(map);
    }

    @Override
    public int userUpdate(Map map) {
         String sex = (String) map.get("sex");
        if (sex.equals("男")) {
            map.put("sex","0");
        } else if (sex.equals("女")){
            map.put("sex","1");
        } else if (sex.equals("未知")) {
            map.put("sex","2");
        }
        return userMapper.userUpdate(map);
    }

    @Override
    public int updateState(int userId, boolean status) {
        return userMapper.updateState(userId, status);
    }

    @Override
    public int userUpdatePwd(Map map) {
        // MD5 加密
        String pwd = (String) map.get("password");
        map.put("password", CommonUtils.MD5(pwd));
        return userMapper.userUpdatePwd(map);
    }
}
