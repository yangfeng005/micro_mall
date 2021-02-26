package com.mall.shop.dto.request;


import com.backstage.core.request.BaseRequest;
import lombok.Data;

import java.io.Serializable;

@Data
public class PurchaseOrderRequest extends BaseRequest implements Serializable {

    /**
     * 订单号
     */
    private String orderSn;
}
