package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.roleImg;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;


public interface RoleImgService {
    PageInfo<roleImg> roleImgFindList(int pageNum, int pageSize);

    int roleImgInsert(Map map);

    int updateRotaShow(int Id, boolean status);

    int update(Map map);

    List<roleImg> publicRole();
}
