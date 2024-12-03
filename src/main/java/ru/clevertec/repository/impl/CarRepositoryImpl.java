package ru.clevertec.repository.impl;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.experimental.SuperBuilder;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.clevertec.entity.Car;
import ru.clevertec.entity.CarShowroom;
import ru.clevertec.enums.SortOrder;
import ru.clevertec.repository.CarRepository;
import ru.clevertec.util.Constants;
import ru.clevertec.util.HibernateUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuperBuilder
public class CarRepositoryImpl extends CrudRepositoryImpl<Car, Long> implements CarRepository {

    @Override
    public void assignCarToShowroom(Long carId, Long carShowroomId) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();

            Car car = session.get(Car.class, carId);
            CarShowroom carShowroom = session.get(CarShowroom.class, carShowroomId);

            if (isCarForAddNotContains(carShowroom.getCars(), carId)) {
                car.setCarShowroom(carShowroom);
                session.merge(car);

                try {
                    session.getTransaction().commit();
                } catch (Exception e) {
                    session.getTransaction().rollback();
                }
            }
        }
    }

    @Override
    public List<Car> getCarsByFilters(String brand, String category, int year, Double minPrice, Double maxPrice) {

        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Car> cbQuery = cb.createQuery(Car.class);
            Root<Car> carRoot = cbQuery.from(Car.class);

            List<Predicate> predicates = new ArrayList<>();
            if (brand != null && !brand.isEmpty()) {
                predicates.add(getEqualsPredicate(cb, carRoot, Constants.CAR_FIELD_BRAND, brand));
            }
            if (category != null && !category.isEmpty()) {
                predicates.add(getEqualsPredicate(cb, carRoot,
                        Constants.CAR_FIELD_CATEGORY,
                        Constants.CAR_FIELD_CATEGORY_NAME,
                        category));
            }
            if (year > 0) {
                predicates.add(getEqualsPredicate(cb, carRoot, Constants.CAR_FIELD_YEAR, year));
            }
            if (minPrice != null) {
                predicates.add(getGreaterThanOrEqualsPredicate(cb, carRoot, Constants.CAR_FIELD_PRICE, minPrice));
            }
            if (maxPrice != null) {
                predicates.add(getLessThanOrEqualsPredicate(cb, carRoot, Constants.CAR_FIELD_PRICE, maxPrice));
            }

            cbQuery.select(carRoot).where(cb.and(
                    predicates.toArray(new Predicate[0])
            ));

            return session.createQuery(cbQuery).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<Car> getCarsSortByPrice(SortOrder sortOrder) {

        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Car> cbQuery = cb.createQuery(Car.class);
            Root<Car> carRoot = cbQuery.from(Car.class);

            if (sortOrder != null) {
                addSortOrder(cb, cbQuery, carRoot, sortOrder);
            }

            return session.createQuery(cbQuery).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<Car> getAllCarsByPage(int page, int pageSize) {

        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Car> cbQuery = cb.createQuery(Car.class);
            Root<Car> carRoot = cbQuery.from(Car.class);
            cbQuery.select(carRoot);

            Query<Car> query = session.createQuery(cbQuery);
            int offset = (page - 1) * pageSize;
            query.setFirstResult(offset);
            query.setMaxResults(pageSize);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private boolean isCarForAddNotContains(List<Car> cars, Long carId) {
        return cars.stream().noneMatch(carInShowroom -> carInShowroom.getId().equals(carId));
    }

    private Predicate getEqualsPredicate(CriteriaBuilder cb, Root root, String field, Object value) {
        return cb.equal(root.get(field), value);
    }

    private Predicate getEqualsPredicate(CriteriaBuilder cb, Root root, String field, String subField, Object value) {
        return cb.equal(root.get(field).get(subField), value);
    }

    private Predicate getGreaterThanOrEqualsPredicate(CriteriaBuilder cb, Root root, String field, Object value) {
        if (value instanceof Double) {
            return cb.greaterThanOrEqualTo(root.get(field), (Double) value);
        }
        return null;
    }

    private Predicate getLessThanOrEqualsPredicate(CriteriaBuilder cb, Root root, String field, Object value) {
        if (value instanceof Double) {
            return cb.lessThanOrEqualTo(root.get(field), (Double) value);
        }
        return null;
    }

    private void addSortOrder(CriteriaBuilder cb, CriteriaQuery<Car> cbQuery, Root<Car> carRoot, SortOrder sortOrder) {
        switch (sortOrder) {
            case ASC:
                cbQuery.orderBy(cb.asc(carRoot.get(Constants.CAR_FIELD_PRICE)));
                break;
            case DESC:
                cbQuery.orderBy(cb.desc(carRoot.get(Constants.CAR_FIELD_PRICE)));
                break;
            default:
                break;
        }
    }

}
