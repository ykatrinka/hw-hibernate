package ru.clevertec.repository.impl;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.clevertec.entity.Review;
import ru.clevertec.repository.ReviewRepository;
import ru.clevertec.util.Constants;
import ru.clevertec.util.HibernateUtil;

import java.util.Collections;
import java.util.List;

@Repository
public class ReviewRepositoryImpl extends CrudRepositoryImpl<Review, Long> implements ReviewRepository {

    @Autowired
    public ReviewRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Review.class);
    }

    @Override
    public List<Review> getAllReviewsFullTextSearch(String searchText) {
        try (Session session = HibernateUtil.getSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Review> cbQuery = cb.createQuery(Review.class);
            Root<Review> reviewRoot = cbQuery.from(Review.class);

            setFullTextPredicate(cb, cbQuery, reviewRoot, searchText);

            return session.createQuery(cbQuery).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private static void setFullTextPredicate(CriteriaBuilder cb, CriteriaQuery<Review> cbQuery, Root<Review> reviewRoot, String searchText) {
        try {
            int searchRating = Integer.parseInt(searchText);
            cbQuery.select(reviewRoot).where(
                    cb.or(
                            cb.like(reviewRoot.get(Constants.REVIEW_FIELD_TEXT),
                                    String.format(Constants.QUERY_LIKE, searchText)),
                            cb.equal(reviewRoot.get(Constants.REVIEW_FIELD_RATING),
                                    searchRating)
                    ));
        } catch (NumberFormatException e) {
            cbQuery.select(reviewRoot).where(
                    cb.like(reviewRoot.get(Constants.REVIEW_FIELD_TEXT),
                            String.format(Constants.QUERY_LIKE, searchText))
            );
        }
    }

}
