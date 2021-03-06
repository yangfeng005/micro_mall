package com.mall.shop.service.impl;

import com.backstage.common.page.Page;
import com.backstage.core.mapper.BaseGeneratedMapper;
import com.backstage.core.result.ServiceResult;
import com.backstage.core.result.ServiceResultHelper;
import com.backstage.core.service.AbstractBaseAOService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.shop.dao.customized.ProductCustomizedMapper;
import com.mall.shop.dao.gen.ProductGeneratedMapper;
import com.mall.shop.dto.request.ProductRequest;
import com.mall.shop.entity.customized.GoodsSpecificationAO;
import com.mall.shop.entity.customized.ProductAO;
import com.mall.shop.entity.gen.ProductCriteria;
import com.mall.shop.service.IGoodsSpecificationService;
import com.mall.shop.service.IProductService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 产品服务
 *
 * @author yangfeng
 */
@Service
public class ProductService extends AbstractBaseAOService<ProductAO, ProductCriteria> implements IProductService {

    @Resource
    private ProductGeneratedMapper productGeneratedMapper;

    @Resource
    private IGoodsSpecificationService goodsSpecificationService;

    @Resource
    private ProductCustomizedMapper productCustomizedMapper;

    @Override
    protected BaseGeneratedMapper<ProductAO, ProductCriteria> getGeneratedMapper() {
        return productGeneratedMapper;
    }


    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @Override
    public ServiceResult<List<ProductAO>> list(ProductRequest request) {
        ServiceResult<List<ProductAO>> ret = new ServiceResult();
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        List<ProductAO> productList = productCustomizedMapper.listByCondition(request);
        if (!CollectionUtils.isEmpty(productList)) {
            productList.stream().forEach(o -> setGoodsSpecification(o));
        }
        ret.setData(productList);
        ret.setSucceed(true);
        ret.setAdditionalProperties("page", Page.obtainPage(new PageInfo(productList)));
        return ret;
    }


    /**
     * 设置商品规格
     *
     * @param o
     */
    @Override
    public void setGoodsSpecification(ProductAO o) {
        if (!StringUtils.isEmpty(o.getGoodsSpecificationIds())) {
            String[] goodsSpecificationIds = o.getGoodsSpecificationIds().split("_");
            if (!Objects.isNull(goodsSpecificationIds) && goodsSpecificationIds.length > 0) {
                List<String> specificationIds = new ArrayList();
                List<String> goodsSpecificationNames = new ArrayList();
                for (String id : goodsSpecificationIds) {
                    GoodsSpecificationAO goodsSpecification = goodsSpecificationService.selectByPrimaryKey(id).getData();
                    specificationIds.add(goodsSpecification.getSpecificationId());
                    goodsSpecificationNames.add(goodsSpecification.getValue());
                }
                o.setGoodsSpecificationIdList(specificationIds);//规格id
                o.setGoodsSpecificationName(String.join(";", goodsSpecificationNames));
            }
        }
    }

    /**
     * 获取商品规格名称
     *
     * @param goodsSpecificationIds
     * @return
     */
    @Override
    public String goodsSpecificationName(String goodsSpecificationIds) {
        List<String> goodsSpecificationNames = new ArrayList();
        if (!StringUtils.isEmpty(goodsSpecificationIds)) {
            String[] goodsSpecificationIdArray = goodsSpecificationIds.split("_");
            if (!Objects.isNull(goodsSpecificationIdArray) && goodsSpecificationIdArray.length > 0) {
                for (String id : goodsSpecificationIdArray) {
                    GoodsSpecificationAO goodsSpecification = goodsSpecificationService.selectByPrimaryKey(id).getData();
                    goodsSpecificationNames.add(goodsSpecification.getValue());
                }
            }
        }
        return String.join(";", goodsSpecificationNames);
    }

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    @Override
    public ServiceResult<ProductAO> getById(String id) {
        return ServiceResultHelper.genResultWithSuccess(productCustomizedMapper.getById(id));
    }
}


