package ru.clevertec.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.clevertec.entity.Car;

import java.math.BigDecimal;
import java.util.Objects;

public class CarSpecification {
    public static Specification<Car> equalsBrand(String brand) {
        if (Objects.isNull(brand) || brand.isEmpty()) {
            return null;
        }
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                .equal(root.get("brand"), brand);
    }

    public static Specification<Car> equalsCategory(String category) {
        if (Objects.isNull(category) || category.isEmpty()) {
            return null;
        }
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                .equal(root.get("category"), category));
    }

    public static Specification<Car> equalsYear(int year) {
        if (year == 0) {
            return null;
        }
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                .equal(root.get("year"), year));
    }

    public static Specification<Car> betweenPrice(BigDecimal minPrice, BigDecimal maxPrice) {
        if (Objects.isNull(minPrice) || Objects.isNull(maxPrice)) {
            return null;
        }

        if (minPrice.equals(0) && maxPrice.equals(0)) {
            return null;
        }

        if (minPrice.equals(0) && !maxPrice.equals(0)) {
            return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                    .le(root.get("price"), maxPrice));
        }

        if (!minPrice.equals(0) && maxPrice.equals(0)) {
            return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                    .ge(root.get("price"), minPrice));
        }

        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                .between(root.get("price"), minPrice, maxPrice));
    }
}
