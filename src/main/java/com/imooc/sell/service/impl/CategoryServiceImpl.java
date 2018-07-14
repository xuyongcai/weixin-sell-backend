package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.ProductCategory;
import com.imooc.sell.repository.ProductCategoryRepository;
import com.imooc.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    public ProductCategory findOne(Integer categoryId) {
        return repository.findOne(categoryId);
    }

    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}
