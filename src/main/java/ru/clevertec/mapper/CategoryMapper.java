package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.dto.CategoryCreateDto;
import ru.clevertec.dto.CategoryResponse;
import ru.clevertec.dto.CategoryUpdateDto;
import ru.clevertec.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    Category createDtoToEntity(CategoryCreateDto categoryRequest);

    @Mapping(target = "id", source = "categoryId")
    Category updateDtoToEntity(CategoryUpdateDto categoryUpdateDto, Long categoryId);

    CategoryResponse entityToResponse(Category category);

}
