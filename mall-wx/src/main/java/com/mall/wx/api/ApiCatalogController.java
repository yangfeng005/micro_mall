package com.mall.wx.api;

import com.backstage.core.result.ServiceResultHelper;
import com.mall.shop.dto.request.CategoryRequest;
import com.mall.shop.entity.customized.CategoryAO;
import com.mall.shop.service.ICategoryService;
import com.mall.wx.annoation.IgnoreAuth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "分类")
@RestController
@RequestMapping("/api/catalog")
public class ApiCatalogController extends ApiBaseController {
    @Autowired
    private ICategoryService categoryService;

    /**
     * 获取分类栏目数据
     */
    @ApiOperation(value = "获取分类栏目数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "id", paramType = "query", required = false),
            @ApiImplicitParam(name = "page", value = "page", paramType = "query", required = false),
            @ApiImplicitParam(name = "size", value = "size", paramType = "query", required = false)})
    @IgnoreAuth
    @PostMapping(value = "index")
    public Object index(String id,
                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                        @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Map<String, Object> resultObj = new HashMap();
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setOrder("rank");
        categoryRequest.setParentCode("");
        //查询列表数据
        List<CategoryAO> data = categoryService.listByContidion(categoryRequest).getData();
        CategoryAO currentCategory = null;
        if (!StringUtils.isEmpty(id)) {
            currentCategory = categoryService.selectByPrimaryKey(id).getData();
        }
        if (null == currentCategory && !CollectionUtils.isEmpty(data)) {
            currentCategory = data.get(0);
        } else {
            currentCategory = new CategoryAO();
        }

        //获取子分类数据
        if (null != currentCategory && !StringUtils.isEmpty(currentCategory.getCode())) {
            categoryRequest.setParentCode(currentCategory.getCode());
            currentCategory.setSubCategoryList(categoryService.listByContidion(categoryRequest).getData());
        }

        resultObj.put("categoryList", data);
        resultObj.put("currentCategory", currentCategory);
        return ServiceResultHelper.genResultWithSuccess(resultObj);
    }

    /**
     *
     */
    @ApiOperation(value = "分类目录当前分类数据接口")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "id", paramType = "query", required = false)})
    @IgnoreAuth
    @PostMapping(value = "current")
    public Object current(String id) {
        Map<String, Object> resultObj = new HashMap();
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setOrder("rank");
        categoryRequest.setParentCode("");
        CategoryAO currentCategory = null;
        if (!StringUtils.isEmpty(id)) {
            currentCategory = categoryService.selectByPrimaryKey(id).getData();
        }
        //获取子分类数据
        if (null != currentCategory && !StringUtils.isEmpty(currentCategory.getCode())) {
            categoryRequest.setParentCode(currentCategory.getCode());
            currentCategory.setSubCategoryList(categoryService.listByContidion(categoryRequest).getData());
        }
        resultObj.put("currentCategory", currentCategory);
        return ServiceResultHelper.genResultWithSuccess(resultObj);
    }
}

