package com.mall.shop.dto.request;


import com.backstage.core.request.BaseRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GoodsRequest extends BaseRequest implements Serializable {


    private String name;

    private String brandId;

    private String categoryId;

    private Boolean isNew;

    private Boolean isHot;

    private Boolean isOnSale;

    private Boolean isDelete;

    private List<String> categoryIds;

    private List<String> goodsIds;

    private String order;

    /**
     * 排除的商品id
     */
    private String excludeGoodsId;
}
