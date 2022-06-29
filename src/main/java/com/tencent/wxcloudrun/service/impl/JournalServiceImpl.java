package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.model.Journal;
import com.tencent.wxcloudrun.dao.JournalMapper;
import com.tencent.wxcloudrun.service.JournalService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    private JournalMapper journalMapper;

    @Override
    public Object journaList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Journal> JournalList = journalMapper.JournalList();
        PageInfo<Journal> pageInfo = new PageInfo<>(JournalList);
        return pageInfo;
    }

    @Override
    public int journalInsert(Map map) {
        return journalMapper.journalInsert(map);
    }

    @Override
    public int update(Map map) {
        return journalMapper.update(map);
    }

    @Override
    public int journalShow(int id, boolean show) {
        return journalMapper.updateShow(id,show);
    }
}
