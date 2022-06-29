package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.model.TypeName;
import com.tencent.wxcloudrun.model.TypeOne;
import com.tencent.wxcloudrun.dao.TypeOneMapper;
import com.tencent.wxcloudrun.service.TypeOneService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TypeOneServiceImpl implements TypeOneService {

    @Autowired
    private TypeOneMapper typeOneMapper;


    @Override
    public PageInfo<TypeOne> typeOneFindList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TypeOne> TypeOneList = typeOneMapper.findAll();
        PageInfo<TypeOne> pageInfo = new PageInfo<>(TypeOneList);
        return pageInfo;
    }

    @Override
    public int typeOneInsert(Map map) {
        return typeOneMapper.typeOneInsert(map);
    }

    @Override
    public int update(Map map) {
        return typeOneMapper.update(map);
    }

    @Override
    public int updateTypeOneShow(int id, boolean show) {
        return typeOneMapper.updateTypeOneShow(id,show);
    }

    @Override
    public List<TypeName> typeName() {
        return typeOneMapper.typeName();
    }

    @Override
    public List<TypeName> publicTypeName() {
        return typeOneMapper.publicTypeName();
    }
}
