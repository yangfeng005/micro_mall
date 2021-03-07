package com.mall.shop.dto.request;


import com.backstage.core.request.BaseRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CartRequest extends BaseRequest implements Serializable {

    private String userId;


    private String goodsId;


    private String productId;


    private List<String> productIds;


    private Boolean checked;
}
