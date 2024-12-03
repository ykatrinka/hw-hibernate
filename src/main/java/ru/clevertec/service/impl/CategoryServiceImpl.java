package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.dto.CategoryRequest;
import ru.clevertec.dto.CategoryResponse;
import ru.clevertec.entity.Category;
import ru.clevertec.exception.CategoryBadRequestException;
import ru.clevertec.exception.CategoryNotFoundException;
import ru.clevertec.mapper.CategoryMapper;
import ru.clevertec.repository.CategoryRepository;
import ru.clevertec.service.CategoryService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public void saveCategory(CategoryRequest categoryRequest) {
        checkCategoryRequest(categoryRequest);

        Category category = categoryMapper.requestToEntity(categoryRequest);
        categoryRepository.save(category);
    }

    @Override
    public CategoryResponse getCategoryById(Long categoryId) {
        checkCategotyId(categoryId);

        Category category = categoryRepository.getById(categoryId);
        return categoryMapper.entityToResponse(category);
    }

    @Override
    public void updateCategory(CategoryRequest categoryRequest, Long categoryId) {
        checkCategoryRequest(categoryRequest);
        checkCategotyId(categoryId);

        Category category = categoryMapper.requestToEntity(categoryRequest, categoryId);
        categoryRepository.update(category);
    }

    @Override
    public void deleteCategoryById(Long categoryId) {
        checkCategotyId(categoryId);

        categoryRepository.deleteById(categoryId);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.getAll()
                .stream()
                .map(categoryMapper::entityToResponse)
                .toList();
    }

    private void checkCategoryRequest(CategoryRequest categoryRequest) {
        if (categoryRequest == null) {
            throw CategoryBadRequestException.byCategoryRequest();
        }
    }

    private void checkCategotyId(Long categoryId) {
        Optional.ofNullable(categoryId)
                .map(categoryRepository::getById)
                .orElseThrow(() -> CategoryNotFoundException.byCategoryId(categoryId));
    }

}
