package com.sea.be.demo.Service.Impl;

import com.sea.be.demo.Dto.CategoryRequest;
import com.sea.be.demo.Entity.Category;
import com.sea.be.demo.Repository.CategoryRepository;
import com.sea.be.demo.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(CategoryRequest request) {
        Category category = Category.builder().name(request.getName()).build();
        categoryRepository.save(category);
    }
}
