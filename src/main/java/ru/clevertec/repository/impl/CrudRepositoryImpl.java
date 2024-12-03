package ru.clevertec.repository.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.clevertec.repository.CrudRepository;
import ru.clevertec.util.Constants;

import java.util.List;

@RequiredArgsConstructor
public class CrudRepositoryImpl<T, K> implements CrudRepository<T, K> {

    private final SessionFactory sessionFactory;
    private final Class<T> cls;

    @Override
    public void save(T t) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(t);
            session.getTransaction().commit();
        }
    }

    @Override
    public T getById(K id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            return session.get(cls, id);
        }
    }

    @Override
    public void update(T t) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(t);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteById(K id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            T t = session.get(cls, id);
            if (t != null) {
                session.remove(t);
                session.getTransaction().commit();
            }
        }
    }

    @Override
    public List<T> getAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String queryFindAll = String.format(Constants.QUERY_FIND_ALL, cls.getName());
            Query<T> query = session.createQuery(queryFindAll, cls);
            return query.list();
        }
    }
}
