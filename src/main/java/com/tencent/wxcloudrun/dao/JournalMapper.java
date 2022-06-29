package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.Journal;

import java.util.List;
import java.util.Map;

public interface JournalMapper {
    List<Journal> JournalList();

    int journalInsert(Map map);

    int update(Map map);

    int updateShow(int id, boolean show);
}
