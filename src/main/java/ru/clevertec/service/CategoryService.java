package ru.clevertec.service;

import ru.clevertec.dto.CategoryCreateDto;
import ru.clevertec.dto.CategoryResponse;
import ru.clevertec.dto.CategoryUpdateDto;

import java.util.List;

public interface CategoryService {
    void saveCategory(CategoryCreateDto categoryRequest);

    CategoryResponse getCategoryById(Long categoryId);

    void updateCategory(Long categoryId, CategoryUpdateDto categoryRequest);

    void deleteCategoryById(Long categoryId);

    List<CategoryResponse> getAllCategories();
}
