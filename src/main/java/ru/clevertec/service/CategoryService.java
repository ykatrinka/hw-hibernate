package ru.clevertec.service;

import ru.clevertec.dto.CategoryRequest;
import ru.clevertec.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    void saveCategory(CategoryRequest categoryRequest);

    CategoryResponse getCategoryById(Long categoryId);

    void updateCategory(CategoryRequest categoryRequest, Long categoryId);

    void deleteCategoryById(Long categoryId);

    List<CategoryResponse> getAllCategories();
}
