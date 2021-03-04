package com.mall.shop.service.impl;

import com.backstage.common.page.Page;
import com.backstage.core.mapper.BaseGeneratedMapper;
import com.backstage.core.result.ServiceResult;
import com.backstage.core.result.ServiceResultHelper;
import com.backstage.core.service.AbstractBaseAOService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.shop.dao.customized.GoodsGalleryCustomizedMapper;
import com.mall.shop.dao.gen.GoodsGalleryGeneratedMapper;
import com.mall.shop.dto.request.GoodsGalleryRequest;
import com.mall.shop.entity.customized.GoodsGalleryAO;
import com.mall.shop.entity.gen.GoodsGalleryCriteria;
import com.mall.shop.service.IGoodsGalleryService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品轮播图服务
 *
 * @author yangfeng
 */
@Service
public class GoodsGalleryService extends AbstractBaseAOService<GoodsGalleryAO, GoodsGalleryCriteria> implements IGoodsGalleryService {

    @Resource
    private GoodsGalleryGeneratedMapper goodsGalleryGeneratedMapper;

    @Resource
    private GoodsGalleryCustomizedMapper goodsGalleryCustomizedMapper;

    @Override
    protected BaseGeneratedMapper<GoodsGalleryAO, GoodsGalleryCriteria> getGeneratedMapper() {
        return goodsGalleryGeneratedMapper;
    }


    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @Override
    public ServiceResult<List<GoodsGalleryAO>> list(GoodsGalleryRequest request) {
        ServiceResult<List<GoodsGalleryAO>> ret = new ServiceResult();
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        List<GoodsGalleryAO> goodsGalleryAOList = goodsGalleryCustomizedMapper.listByCondition(request);
        ret.setData(goodsGalleryAOList);
        ret.setSucceed(true);
        ret.setAdditionalProperties("page", Page.obtainPage(new PageInfo(goodsGalleryAOList)));
        return ret;
    }


    @Override
    public ServiceResult<List<GoodsGalleryAO>> listByCondition(GoodsGalleryRequest request) {
        return ServiceResultHelper.genResultWithSuccess(goodsGalleryCustomizedMapper.listByCondition(request));
    }


    /**
     * 保存商品轮播图
     *
     * @param goodsId
     * @param galleryImgIds
     * @return
     */
    @Override
    public ServiceResult<Boolean> save(String goodsId, List<String> galleryImgIds) {
        GoodsGalleryCriteria criteria = new GoodsGalleryCriteria();
        criteria.createCriteria().andGoodsIdEqualTo(goodsId);
        deleteByCriteria(criteria);
        if (!CollectionUtils.isEmpty(galleryImgIds)) {
            for (int i = 0; i < galleryImgIds.size(); i++) {
                GoodsGalleryAO tmp = new GoodsGalleryAO();
                tmp.setGoodsId(goodsId);
                tmp.setImgUrl(galleryImgIds.get(i));
                tmp.setSortOrder(i + 1);
                insert(tmp);
            }
        }
        return ServiceResultHelper.genResultWithSuccess();
    }
}

