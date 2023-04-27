package com.ray.design.general.service;

import com.alibaba.fastjson.JSON;
import com.ray.design.general.req.DeliverRequest;

/**
 * 模拟实物商品服务
 */
public class GoodsService {

    public Boolean deliverGoods(DeliverRequest req) {
        System.out.println("模拟发货实物商品一个：" + JSON.toJSONString(req));
        return true;
    }

}