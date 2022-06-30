package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.MenuSystem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuSystemMapper {
    List<MenuSystem> findRouter();
}
