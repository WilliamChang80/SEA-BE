package com.sea.be.demo.Service;


import com.sea.be.demo.Dto.CategoryRequest;
import com.sea.be.demo.Entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    void createCategory(CategoryRequest request);
}
