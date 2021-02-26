package com.mall.shop.service.impl;

import com.backstage.common.page.Page;
import com.backstage.core.mapper.BaseGeneratedMapper;
import com.backstage.core.result.ServiceResult;
import com.backstage.core.result.ServiceResultHelper;
import com.backstage.core.service.AbstractBaseAOService;
import com.backstage.system.auth.AuthUtil;
import com.backstage.system.entity.customized.UserAO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.shop.dao.customized.GoodsCustomizedMapper;
import com.mall.shop.dao.gen.GoodsGeneratedMapper;
import com.mall.shop.dto.request.GoodsRequest;
import com.mall.shop.entity.customized.GoodsAO;
import com.mall.shop.entity.gen.GoodsCriteria;
import com.mall.shop.service.IGoodsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 商品服务
 *
 * @author yangfeng
 */
@Service
public class GoodsService extends AbstractBaseAOService<GoodsAO, GoodsCriteria> implements IGoodsService {

    @Resource
    private GoodsGeneratedMapper goodsGeneratedMapper;

    @Resource
    private GoodsCustomizedMapper goodsCustomizedMapper;

    @Override
    protected BaseGeneratedMapper<GoodsAO, GoodsCriteria> getGeneratedMapper() {
        return goodsGeneratedMapper;
    }


    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @Override
    public ServiceResult<List<GoodsAO>> list(GoodsRequest request) {
        ServiceResult<List<GoodsAO>> ret = new ServiceResult();
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        List<GoodsAO> goodsAOList = goodsCustomizedMapper.listByCondition(request);
        ret.setData(goodsAOList);
        ret.setSucceed(true);
        ret.setAdditionalProperties("page", Page.obtainPage(new PageInfo(goodsAOList)));
        return ret;
    }


    @Override
    public ServiceResult<List<GoodsAO>> listAll(GoodsRequest request) {
        return ServiceResultHelper.genResultWithSuccess(goodsCustomizedMapper.listByCondition(request));
    }

    @Transactional
    @Override
    public ServiceResult<Boolean> enSale(String[] goodsIds) {
        UserAO user = AuthUtil.getCurrentUser();
        if (goodsIds != null && goodsIds.length > 0) {
            for (String goodsId : goodsIds) {
                GoodsAO goods = selectByPrimaryKey(goodsId).getData();
                goods.setIsOnSale(true);
                goods.setUpdateUserId(user.getId());
                goods.setUpdateTime(new Date());
                saveOrUpdate(goods);
            }
        }
        return ServiceResultHelper.genResultWithSuccess();
    }

    @Transactional
    @Override
    public ServiceResult<Boolean> unSale(String[] goodsIds) {
        UserAO user = AuthUtil.getCurrentUser();
        if (goodsIds != null && goodsIds.length > 0) {
            for (String goodsId : goodsIds) {
                GoodsAO goods = selectByPrimaryKey(goodsId).getData();
                goods.setIsOnSale(false);
                goods.setUpdateUserId(user.getId());
                goods.setUpdateTime(new Date());
                saveOrUpdate(goods);
            }
        }
        return ServiceResultHelper.genResultWithSuccess();
    }

}

