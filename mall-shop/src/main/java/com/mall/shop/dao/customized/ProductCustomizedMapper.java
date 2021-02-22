package com.mall.shop.dao.customized;

import com.mall.shop.dto.request.ProductRequest;
import com.mall.shop.entity.customized.ProductAO;

import java.util.List;

/**
 * 自动生成的 Product 数据存取接口.
 *
 * <p>
 * 该类于 2021-02-22 10:36:23 生成，请勿手工修改！
 * </p>
 *
 * @author yangfeng
 * @version 1.0.0, Feb 22, 2021
 */
public interface ProductCustomizedMapper {

    List<ProductAO> listByCondition(ProductRequest request);
}
