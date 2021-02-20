package com.mall.shop.dao.customized;


import com.mall.shop.entity.customized.CategoryAO;
import com.mall.shop.dto.request.CategoryRequest;

import java.util.List;

public interface CategoryCustomizedMapper {

    List<CategoryAO> list(CategoryRequest request);


    /**
     * 编辑时选择上级机构要排除自身
     *
     * @param id
     * @return
     */
    List<CategoryAO> listOthers(String id);


    /**
     * 批量删除
     *
     * @param categoryAOList
     * @return
     */
    int deleteBatch(List<CategoryAO> categoryAOList);
}