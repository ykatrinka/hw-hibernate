package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.dto.CategoryRequest;
import ru.clevertec.dto.CategoryResponse;
import ru.clevertec.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cars", ignore = true)
    Category requestToEntity(CategoryRequest categoryRequest);

    @Mapping(target = "id", source = "categoryId")
    @Mapping(target = "cars", ignore = true)
    Category requestToEntity(CategoryRequest categoryRequest, Long categoryId);

    CategoryResponse entityToResponse(Category category);

}
