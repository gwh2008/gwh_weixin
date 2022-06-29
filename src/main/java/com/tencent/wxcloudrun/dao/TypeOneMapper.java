package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.TypeName;
import com.tencent.wxcloudrun.model.TypeOne;

import java.util.List;
import java.util.Map;

public interface TypeOneMapper {
    List<TypeOne> findAll();

    int typeOneInsert(Map map);

    int update(Map map);

    int updateTypeOneShow(int id, boolean show);

    List<TypeName> typeName();

    List<TypeName> publicTypeName();
}
