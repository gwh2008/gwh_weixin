package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.roleImg;

import java.util.List;
import java.util.Map;

public interface RoleImgMapper {

    List<roleImg> findRoleImgList();

    int insertRole(Map map);

    int updateRotaShow(int Id, boolean status);

    int update(Map map);

    List<roleImg> publicRole();
}
