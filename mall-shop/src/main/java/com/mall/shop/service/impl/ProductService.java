package com.mall.shop.service.impl;

import com.backstage.common.page.Page;
import com.backstage.core.mapper.BaseGeneratedMapper;
import com.backstage.core.result.ServiceResult;
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
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        //设置商品规格
        setGoodsSpecification(productList);
        ret.setData(productList);
        ret.setSucceed(true);
        ret.setAdditionalProperties("page", Page.obtainPage(new PageInfo(productList)));
        return ret;
    }

    public void setGoodsSpecification(List<ProductAO> productList) {
        if (!CollectionUtils.isEmpty(productList)) {
            productList.stream().forEach(o -> {
                List<GoodsSpecificationAO> data = goodsSpecificationService
                        .getSpecificationByGoodsId(o.getGoodsId()).getData();
                Map<String, List<GoodsSpecificationAO>> specificationMap = data.stream()
                        .collect(Collectors.groupingBy(GoodsSpecificationAO::getSpecificationName,
                                LinkedHashMap::new, Collectors.toList()));
                o.setSpecificationMap(specificationMap);
                if(!StringUtils.isEmpty(o.getGoodsSpecificationIds())){
                    o.setGoodsSpecificationIdList(Arrays.asList(o.getGoodsSpecificationIds().split("_")));
                }
            });
        }
    }

}

