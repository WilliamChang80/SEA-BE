package com.sea.be.demo.Controller;

import com.sea.be.demo.Dto.BaseResponse;
import com.sea.be.demo.Dto.CategoryRequest;
import com.sea.be.demo.Entity.Category;
import com.sea.be.demo.Enum.HttpResponse;
import com.sea.be.demo.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public BaseResponse getAllCategories() {
        List<Category> categoryList = categoryService.getAllCategories();
        return BaseResponse.builder().code(HttpResponse.SUCCESS.getCode()).message("Success")
                .data(categoryList).build();
    }

    @PostMapping("/category")
    public BaseResponse createCategory(@RequestBody CategoryRequest request) {
        categoryService.createCategory(request);
        return BaseResponse.builder().code(HttpResponse.SUCCESS.getCode()).message("Success").build();
    }
}
