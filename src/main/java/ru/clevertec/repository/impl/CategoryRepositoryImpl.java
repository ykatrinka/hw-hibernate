package ru.clevertec.repository.impl;

import lombok.experimental.SuperBuilder;
import ru.clevertec.entity.Category;
import ru.clevertec.repository.CategoryRepository;

@SuperBuilder
public class CategoryRepositoryImpl extends CrudRepositoryImpl<Category, Long> implements CategoryRepository {
}
