package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.dto.CategoryCreateDto;
import ru.clevertec.dto.CategoryResponse;
import ru.clevertec.dto.CategoryUpdateDto;
import ru.clevertec.entity.Category;
import ru.clevertec.exception.CategoryNotFoundException;
import ru.clevertec.mapper.CategoryMapper;
import ru.clevertec.repository.CategoryRepository;
import ru.clevertec.service.CategoryService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public void saveCategory(CategoryCreateDto categoryCreateDto) {
        Category category = categoryMapper.createDtoToEntity(categoryCreateDto);
        categoryRepository.save(category);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .map(categoryMapper::entityToResponse)
                .orElseThrow(() -> CategoryNotFoundException.byCategoryId(categoryId));
    }

    @Override
    public void updateCategory(Long categoryId, CategoryUpdateDto categoryUpdateDto) {
        checkCategoryId(categoryId);

        Optional.ofNullable(categoryUpdateDto)
                .map(categoryDto -> categoryMapper.updateDtoToEntity(categoryDto, categoryId))
                .ifPresent(categoryRepository::save);
    }

    @Override
    public void deleteCategoryById(Long categoryId) {
        Optional.ofNullable(categoryId)
                .map(categoryRepository::findById)
                .ifPresent(id -> categoryRepository.deleteById(categoryId));
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::entityToResponse)
                .toList();
    }

    private void checkCategoryId(Long categoryId) {
        Optional.ofNullable(categoryId)
                .map(categoryRepository::findById)
                .orElseThrow(() -> CategoryNotFoundException.byCategoryId(categoryId));
    }

}
