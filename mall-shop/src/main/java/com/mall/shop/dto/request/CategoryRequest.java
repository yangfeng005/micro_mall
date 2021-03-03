package com.mall.shop.dto.request;


import com.backstage.core.request.BaseRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CategoryRequest extends BaseRequest implements Serializable {

    /**
     * 名称
     */
    private String name;


    /**
     * 编码
     */
    private String code;


    private String notName;


    private String parentCode;

    private String order;

    private List<String> ids;
}
