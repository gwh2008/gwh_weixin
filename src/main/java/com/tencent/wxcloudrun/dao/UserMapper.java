package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    List<User> findUserList(String userName, String nickName);

    Integer findUserTotal();

    Boolean findUserByUserName(String username);

    User loginUser(String password, String username);

    int deleteUser(String user_id);

    int insertUser(Map map);

    int userUpdate(Map map);

    int updateState(int userId, boolean status);

    int userUpdatePwd(Map map);
}
