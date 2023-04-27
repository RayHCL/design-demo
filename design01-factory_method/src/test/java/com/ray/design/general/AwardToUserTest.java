package com.ray.design.general;


import com.alibaba.fastjson.JSON;
import com.ray.design.general.req.AwardRequest;
import com.ray.design.general.res.AwardResponse;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class AwardToUserTest {

    private Logger logger = LoggerFactory.getLogger(AwardToUserTest.class);

    @Test
    public void testAwardCouponToUser() {
        PrizeController prizeController = new PrizeController();
        System.out.println("\r\n模拟测试发放优惠券\r\n");
        AwardRequest awardRequest = new AwardRequest("10001", 1, "RM1000988783322", "545457772", new HashMap<String, String>());
        AwardResponse awardResponse = prizeController.awardToUser(awardRequest);
        logger.info("请求参数：{}", JSON.toJSON(awardRequest));
        logger.info("测试结果：{}", JSON.toJSON(awardResponse));
    }
    @Test
    public void  testAwardGoodsToUser(){
        PrizeController prizeController = new PrizeController();
        System.out.println("\r\n模拟测试发放实物商品\r\n");

        Map<String, String> extMap = new HashMap<String, String>();
        extMap.put("consigneeUserName","ray");
        extMap.put("consigneeUserPhone","17857128270");
        extMap.put("consigneeUserAddress","浙江省杭州市西湖区xxx");
        AwardRequest awardRequest = new AwardRequest("1001", 2, "RMb4656666", "8977857772",extMap);
        AwardResponse awardResponse = prizeController.awardToUser(awardRequest);
        logger.info("请求参数：{}", JSON.toJSON(awardRequest));
        logger.info("测试结果：{}", JSON.toJSON(awardResponse));
    }


    @Test
    public void testAwardIQiYiCardToUser() {
        PrizeController prizeController = new PrizeController();
        System.out.println("\r\n模拟测试发放第三⽅兑换卡(爱奇艺)\r\n");
        AwardRequest awardRequest = new AwardRequest("10001", 3, "RM1000988783322", "545457772", null);
        AwardResponse awardResponse = prizeController.awardToUser(awardRequest);
        logger.info("请求参数：{}", JSON.toJSON(awardRequest));
        logger.info("测试结果：{}", JSON.toJSON(awardResponse));
    }
}
