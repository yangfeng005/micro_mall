package com.mall.shop.dto.request;


import com.backstage.core.request.BaseRequest;
import lombok.Data;

import java.io.Serializable;

@Data
public class AdRequest extends BaseRequest implements Serializable {

    /**
     * 名称
     */
    private String name;
}
