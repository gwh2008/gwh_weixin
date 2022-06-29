package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.model.goods;
import com.tencent.wxcloudrun.dao.GoodsMapper;
import com.tencent.wxcloudrun.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public Object goodsFindList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<goods> goodsList = goodsMapper.goodsFindList();
        PageInfo<goods> pageInfo = new PageInfo<>(goodsList);
        return pageInfo;
    }

    @Override
    public int update(Map map) {
        return goodsMapper.update(map);
    }

    @Override
    public int insert(Map map) {
        return goodsMapper.insert(map);
    }

    @Override
    public int updateShow(int id, boolean goodsShow) {
        return goodsMapper.updateShow(id,goodsShow);
    }

    @Override
    public int updateRecommend(int id, boolean recommend) {
        return  goodsMapper.updateRecommend(id,recommend);
    }

    @Override
    public List<goods> goodFirstImg(int typeId) {
        return goodsMapper.goodFirstImg(typeId);
    }

    @Override
    public Object recommend() {
        return goodsMapper.recommend();
    }

    @Override
    public Object goodDetail(int id) {
        return goodsMapper.goodDetail(id);
    }
}
