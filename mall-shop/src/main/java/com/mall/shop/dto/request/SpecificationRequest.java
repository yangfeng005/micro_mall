package com.mall.shop.dto.request;


import com.backstage.core.request.BaseRequest;
import lombok.Data;

import java.io.Serializable;

@Data
public class SpecificationRequest extends BaseRequest implements Serializable {

    /**
     * 规格名称
     */
    private String name;
}
