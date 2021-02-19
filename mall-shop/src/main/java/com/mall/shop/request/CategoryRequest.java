package com.mall.shop.request;


import com.backstage.core.request.BaseRequest;
import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryRequest extends BaseRequest implements Serializable {

    /**
     * 规格名称
     */
    private String name;
}
