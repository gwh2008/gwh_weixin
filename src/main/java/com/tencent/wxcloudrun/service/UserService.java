package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.User;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface UserService {

    PageInfo<User> userFindList(int pageNum, int pageSize, String userName, String nickName);

    User userLogin(String password, String username);

    Boolean usrFindByUsername(String username);

    Object userFindTotal();

    int userDelete(String userId);

    int userInsert(Map map);

    int userUpdate(Map map);

    int updateState(int userId, boolean status);

    int userUpdatePwd(Map map);
}
