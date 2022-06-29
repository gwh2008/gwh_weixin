package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.goods;

import java.util.List;
import java.util.Map;

public interface GoodsMapper {
    List<goods> goodsFindList();

    int update(Map map);

    int insert(Map map);

    int updateShow(int id, boolean goodsShow);

    int updateRecommend(int id, boolean recommend);

    List<goods> goodFirstImg(int typeId);

    List<goods> recommend();

    Object goodDetail(int id);
}
