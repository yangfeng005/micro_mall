package com.mall.shop.dto.request;


import com.backstage.core.request.BaseRequest;
import lombok.Data;

import java.io.Serializable;

@Data
public class OrderGoodsRequest extends BaseRequest implements Serializable {

    /**
     * 订单id
     */
    private String orderId;
}
