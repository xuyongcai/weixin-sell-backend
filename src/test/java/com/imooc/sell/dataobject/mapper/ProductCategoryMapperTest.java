package com.imooc.sell.dataobject.mapper;

import com.imooc.sell.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    @Transactional
    public void insertByMap() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("categoryName", "关心加班的你");
        map.put("category_type", 101);
        int result = mapper.insertByMap(map);
        Assert.assertEquals(1, result);
    }

    @Test
    @Transactional
    public void insertByObject() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("秋意浓");
        productCategory.setCategoryType(102);
        int result = mapper.insertByObject(productCategory);
        Assert.assertEquals(1, result);
    }


    @Test
    public void findByCategoryType() {
        ProductCategory result = mapper.findByCategoryType(102);
        log.info(result.getCategoryName());
        Assert.assertNotNull(result);
    }

    @Test
    public void findByCategoryName() {
        List<ProductCategory> result = mapper.findByCategoryName("男装");
        for(ProductCategory productCategory:result){
            log.info(productCategory.getCategoryName()+"/"+productCategory.getCategoryId());
        }
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    @Transactional
    public void updateByCategoryType() {
        int result = mapper.updateByCategoryType("凛冬已至", 102);
        Assert.assertEquals(1, result);
    }

    @Test
    @Transactional
    public void updateByObject() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("秋意浓");
        productCategory.setCategoryType(101);
        int result = mapper.updateByObject(productCategory);
        Assert.assertEquals(1, result);
    }


    @Test
    @Transactional
    public void deleteByCategoryType() {
        int result = mapper.deleteByCategoryType(102);
        Assert.assertEquals(1, result);
    }


    @Test
    public void selectByCategoryType() {
        ProductCategory productCategory = mapper.selectByCategoryType(101);
        log.info(productCategory.getCategoryName());
        Assert.assertNotNull(productCategory);
    }

}