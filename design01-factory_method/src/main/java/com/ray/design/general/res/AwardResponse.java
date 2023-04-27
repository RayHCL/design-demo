package com.ray.design.general.res;

import lombok.Data;

@Data
public class AwardResponse {

    private String code; // 编码
    private String info; // 描述

    public AwardResponse(String code, String info) {
        this.code = code;
        this.info = info;
    }


}
