package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.roleImg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoleImgMapper {

    List<roleImg> findRoleImgList();

    int insertRole(Map map);

    int updateRotaShow(int Id, boolean status);

    int update(Map map);

    List<roleImg> publicRole();
}
