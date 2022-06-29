package com.tencent.wxcloudrun.service;

import java.util.Map;

public interface GoodsService {
    Object goodsFindList(int pageNum, int pageSize);

    int update(Map map);

    int insert(Map map);

    int updateShow(int id, boolean goodsShow);

    int updateRecommend(int id, boolean recommend);

    Object goodFirstImg(int typeId);

    Object recommend();

    Object goodDetail(int id);
}
