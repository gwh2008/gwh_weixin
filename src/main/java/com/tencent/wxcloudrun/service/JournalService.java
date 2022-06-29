package com.tencent.wxcloudrun.service;

import java.util.Map;

public interface JournalService {
    Object journaList(int pageNum, int pageSize);

    int journalInsert(Map map);

    int update(Map map);

    int journalShow(int id, boolean show);
}
