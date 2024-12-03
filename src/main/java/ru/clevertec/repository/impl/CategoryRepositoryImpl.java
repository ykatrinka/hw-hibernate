package ru.clevertec.repository.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.clevertec.entity.Category;
import ru.clevertec.repository.CategoryRepository;

@Repository
public class CategoryRepositoryImpl extends CrudRepositoryImpl<Category, Long> implements CategoryRepository {

    @Autowired
    public CategoryRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Category.class);
    }
}
