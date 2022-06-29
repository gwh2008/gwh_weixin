package com.tencent.wxcloudrun.service;

import java.util.Map;

public interface TypeOneService {
    Object typeOneFindList(int pageNum, int pageSize);

    int typeOneInsert(Map map);

    int update(Map map);

    int updateTypeOneShow(int id, boolean show);

    Object typeName();

    Object publicTypeName();
}
