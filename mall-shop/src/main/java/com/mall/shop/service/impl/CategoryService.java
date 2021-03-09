package com.mall.shop.service.impl;

import com.backstage.core.mapper.BaseGeneratedMapper;
import com.backstage.core.result.ServiceResult;
import com.backstage.core.result.ServiceResultHelper;
import com.backstage.core.service.AbstractBaseAOService;
import com.backstage.core.tree.TreeUtil;
import com.mall.shop.dao.customized.CategoryCustomizedMapper;
import com.mall.shop.dao.gen.CategoryGeneratedMapper;
import com.mall.shop.dto.request.CategoryRequest;
import com.mall.shop.entity.customized.CategoryAO;
import com.mall.shop.entity.gen.CategoryCriteria;
import com.mall.shop.service.ICategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品类型服务
 *
 * @author yangfeng
 */
@Service
public class CategoryService extends AbstractBaseAOService<CategoryAO, CategoryCriteria> implements ICategoryService {

    @Resource
    private CategoryGeneratedMapper categoryGeneratedMapper;

    @Resource
    private CategoryCustomizedMapper categoryCustomizedMapper;


    @Override
    protected BaseGeneratedMapper<CategoryAO, CategoryCriteria> getGeneratedMapper() {
        return categoryGeneratedMapper;
    }


    /**
     * 查询商品分类
     *
     * @return
     */
    @Override
    public ServiceResult<List<CategoryAO>> list(CategoryRequest request) {
        List<CategoryAO> categoryList = categoryCustomizedMapper.list(null);
        List<CategoryAO> categoryChildren = categoryCustomizedMapper.list(request);
        List<CategoryAO> parentList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(categoryChildren)) {
            for (CategoryAO child : categoryChildren) {
                TreeUtil.getAllParentListWithChild(categoryList, child.getCode(), parentList);
            }
        }
        //排序并构造树结构
        return TreeUtil.sortTreeNodes(parentList, "");
    }


    /**
     * 删除商品分类及所有子商品分类
     *
     * @param categoryId
     * @return
     */
    @Override
    public ServiceResult<Boolean> delete(String categoryId) {
        List<CategoryAO> categoryAOList = categoryCustomizedMapper.list(null);
        if (!CollectionUtils.isEmpty(categoryAOList)) {
            CategoryAO parent = null;
            for (CategoryAO category : categoryAOList) {
                if (category.getId().equals(categoryId)) {
                    parent = category;
                    break;
                }
            }
            //递归获取所有子节点
            if (parent != null) {
                List<CategoryAO> childNodes = new ArrayList<>();
                TreeUtil.getAllChildNodes(parent, categoryAOList, childNodes);
                childNodes.add(parent);
                categoryCustomizedMapper.deleteBatch(childNodes);
            }
        }
        return ServiceResultHelper.genResultWithSuccess();
    }


    /**
     * 保存
     *
     * @param category
     * @return
     */
    @Override
    public ServiceResult<Boolean> save(CategoryAO category) {
        if (category == null || StringUtils.isEmpty(category.getCode())) {
            return ServiceResult.error("参数错误，分类编码不能为空");
        }
        if (!checkBeforeSave(category)) {
            return ServiceResultHelper.genResultWithFaild("分类编码已存在", -1);
        }
        if (null == category.getParentCode()) {
            category.setParentCode("");
        }
        return saveOrUpdate(category);
    }


    /**
     * 保存前进行检查
     *
     * @return
     */
    public boolean checkBeforeSave(CategoryAO category) {
        CategoryCriteria criteria = new CategoryCriteria();
        if (StringUtils.isEmpty(category.getId())) {
            criteria.createCriteria().andCodeEqualTo(category.getCode());
        } else {
            //修改
            criteria.createCriteria().andCodeEqualTo(category.getCode())
                    .andIdNotEqualTo(category.getId());
        }
        List<CategoryAO> result = selectByCriteria(criteria).getData();
        return !CollectionUtils.isEmpty(result) ? false : true;
    }


    @Override
    public ServiceResult<List<CategoryAO>> listByContidion(CategoryRequest request) {
        return ServiceResultHelper.genResultWithSuccess(categoryCustomizedMapper.list(request));
    }


    /**
     * 获取当前分类及其左右子分类
     *
     * @param categoryId
     * @return
     */
    @Override
    public ServiceResult<List<CategoryAO>> listAllChildrenAndSelf(String categoryId) {
        List<CategoryAO> categoryList = categoryCustomizedMapper.list(null);
        if (!CollectionUtils.isEmpty(categoryList)) {
            CategoryAO parent = null;
            for (CategoryAO category : categoryList) {
                if (category.getId().equals(categoryId)) {
                    parent = category;
                    break;
                }
            }
            //递归获取所有子节点
            if (parent != null) {
                List<CategoryAO> childNodes = new ArrayList<>();
                TreeUtil.getAllChildNodes(parent, categoryList, childNodes);
                childNodes.add(parent);
                return ServiceResultHelper.genResultWithSuccess(childNodes);
            }
        }
        return ServiceResultHelper.genResultWithSuccess();
    }
}

