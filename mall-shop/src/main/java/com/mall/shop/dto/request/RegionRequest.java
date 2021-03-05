package com.mall.shop.dto.request;


import com.backstage.core.request.BaseRequest;
import lombok.Data;

import java.io.Serializable;

@Data
public class RegionRequest extends BaseRequest implements Serializable {

    /**
     * 地区编码
     */
    private String code;
}
