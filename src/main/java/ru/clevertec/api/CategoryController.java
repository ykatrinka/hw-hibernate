package ru.clevertec.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.dto.CategoryCreateDto;
import ru.clevertec.dto.CategoryResponse;
import ru.clevertec.dto.CategoryUpdateDto;
import ru.clevertec.service.CategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
@Validated
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCategory(@RequestBody @Valid CategoryCreateDto categoryCreateDto) {
        categoryService.saveCategory(categoryCreateDto);
    }


    @GetMapping(value = "/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponse getCategory(@PathVariable("categoryId") @Valid @NotNull Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @PutMapping(value = "/{categoryId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateCategory(
            @PathVariable("categoryId") @Valid @NotNull Long categoryId,
            @RequestBody @Valid CategoryUpdateDto categoryUpdateDto) {
        categoryService.updateCategory(categoryId, categoryUpdateDto);
    }

    @DeleteMapping(value = "/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@PathVariable("categoryId") @Valid @NotNull Long categoryId) {
        categoryService.deleteCategoryById(categoryId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryResponse> getCategories() {
        return categoryService.getAllCategories();
    }
}
