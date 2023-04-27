package com.ray.design.general;

import com.alibaba.fastjson.JSON;
import com.ray.design.general.req.AwardRequest;
import com.ray.design.general.req.DeliverRequest;
import com.ray.design.general.res.AwardResponse;
import com.ray.design.general.result.CouponResult;
import com.ray.design.general.service.CouponService;
import com.ray.design.general.service.GoodsService;
import com.ray.design.general.service.IQiYiCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PrizeController {

    private Logger logger = LoggerFactory.getLogger(PrizeController.class);

    public AwardResponse awardToUser(AwardRequest awardRequest) {
        AwardResponse awardResponse = null;
        String awardRequestJson = JSON.toJSONString(awardRequest);
        logger.info("奖品发放开始: {}, req: {}", awardRequest.getUId(), awardRequestJson);

        try {
            // 按照不不同类型⽅方法商品[1优惠券、2实物商品、3第三⽅方兑换卡(爱奇艺)
            if (awardRequest.getAwardType() == 1) {
                CouponService couponService = new CouponService();
                CouponResult couponResult =
                        couponService.sendCoupon(awardRequest.getUId(), awardRequest.getAwardNumber(), awardRequest.getBizId());
                if ("0000".equals(couponResult.getCode())) {
                    awardResponse = new AwardResponse("0000", "发放成功");
                } else {
                    awardResponse = new AwardResponse("0001", couponResult.getInfo());
                }
            }
            else if (awardRequest.getAwardType() == 2) {
                GoodsService goodsService = new GoodsService();

                Boolean isSuccess = goodsService.deliverGoods(new DeliverRequest("ray",
                        "17857128270",
                        "lll",
                        "aaaaaa",
                        awardRequest.getExtMap().get("consigneeUserName"),
                        awardRequest.getExtMap().get("consigneeUserPhone"),
                        awardRequest.getExtMap().get("consigneeUserAddress")));
                if (isSuccess) {
                    awardResponse = new AwardResponse("0000", "发放成功");
                } else {
                    awardResponse = new AwardResponse("0001", "发放失败");
                }

            }
            else if (awardRequest.getAwardType() == 3) {
                IQiYiCardService iQiYiCardService = new IQiYiCardService();

                iQiYiCardService.grantToken("17857128270",awardRequest.getAwardNumber());
                awardResponse = new AwardResponse("0000", "发放成功");
            }
            logger.info("奖品发放完成：{}。",awardRequest.getUId());
        }catch (Exception e){
            logger.error("奖品发送失败: {},req: {}",awardRequest.getUId(),awardRequestJson);
            awardResponse=new AwardResponse("0001",e.getMessage());
        }



        return awardResponse;
    }

}
