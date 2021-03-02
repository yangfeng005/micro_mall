package com.mall.shop.controller;

import com.backstage.system.log.LogOperation;
import com.mall.shop.dto.request.CategoryRequest;
import com.mall.shop.entity.customized.CategoryAO;
import com.mall.shop.service.ICategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "商品类型管理")
@RestController
@RequestMapping("/category")
public class CategoryController {

    private static Logger LOG = LoggerFactory.getLogger(CategoryController.class);

    @Resource
    private ICategoryService categoryService;


    /**
     * 查询商品类型树形结构数据
     *
     * @return
     */
    @ApiOperation(value = "查询商品类型树形结构数据", notes = "查询商品类型树形结构数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "分类名称", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "code", value = "分类编码", dataType = "String", paramType = "query", required = false),
    })
    @PostMapping("/list")
    @RequiresPermissions(value = {"category:view", "category:manage"}, logical = Logical.OR)
    @LogOperation(action = "查询商品类型树形结构数据")
    public Object list(CategoryRequest request) {
        return categoryService.list(request);
    }


    /**
     * 保存商品类型
     *
     * @param category 前端传入的数据对象.
     * @return
     */
    /**
     * 保存商品类型
     *
     * @param category 前端传入的数据对象.
     * @return
     */
    @ApiOperation(value = "保存商品类型", notes = "保存商品类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id,编辑时不能为空", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "name", value = "分类名称", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "code", value = "分类编码", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "parentCode", value = "父级分类编码", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "rank", value = "排序", dataType = "Integer", paramType = "query", required = true),
            @ApiImplicitParam(name = "frontDesc", value = "描述", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "showIndex", value = "首页展示排序", dataType = "Integer", paramType = "query", required = false),
            @ApiImplicitParam(name = "isShow", value = "是否显示", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "bannerUrl", value = "banner图片", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "wapBannerUrl", value = "手机banner", dataType = "String", paramType = "query", required = false),
    })
    @PostMapping("/save")
    @RequiresPermissions("category:manage")
    @LogOperation(action = "保存商品类型")
    public Object save(CategoryAO category) {
        return categoryService.save(category);
    }


    /**
     * 删除商品类型
     *
     * @param categoryId 主键id .
     * @return
     */
    @ApiOperation(value = "删除商品类型", notes = "删除商品类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryId", value = "id", dataType = "String", paramType = "query", required = true),
    })
    @GetMapping("/delete")
    @RequiresPermissions("category:manage")
    @LogOperation(action = "删除商品类型")
    public Object delete(@RequestParam String categoryId) {
        return categoryService.delete(categoryId);
    }

}
