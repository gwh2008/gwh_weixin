package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.model.roleImg;
import com.tencent.wxcloudrun.dao.RoleImgMapper;
import com.tencent.wxcloudrun.service.RoleImgService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoleImgServiceImpl implements RoleImgService {

//    @Autowired
//    private RoleImgMapper roleImgMapper;
     final RoleImgMapper roleImgMapper;

    public RoleImgServiceImpl(RoleImgMapper roleImgMapper) {
        this.roleImgMapper = roleImgMapper;
    }

    @Override
    public PageInfo<roleImg> roleImgFindList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<roleImg> roleImgList = roleImgMapper.findRoleImgList();
        PageInfo<roleImg> pageInfo = new PageInfo<>(roleImgList);
        return pageInfo;
    }

    @Override
    public int roleImgInsert(Map map) {
        return roleImgMapper.insertRole(map);
    }

    @Override
    public int updateRotaShow(int Id, boolean status) {
        return roleImgMapper.updateRotaShow(Id,status);
    }

    @Override
    public int update(Map map) {
        return roleImgMapper.update(map);
    }

    @Override
    public List<roleImg> publicRole() {
        return roleImgMapper.publicRole();
    }
}
