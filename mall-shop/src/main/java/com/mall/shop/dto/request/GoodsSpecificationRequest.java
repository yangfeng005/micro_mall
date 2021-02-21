package com.mall.shop.dto.request;


import com.backstage.core.request.BaseRequest;
import lombok.Data;

import java.io.Serializable;

@Data
public class GoodsSpecificationRequest extends BaseRequest implements Serializable {

    /**
     * 商品名称
     */
    private String productName;
}
