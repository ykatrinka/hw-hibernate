package ru.clevertec.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
    public static final String QUERY_FIND_ALL = "from %s";
    public static final String CAR_FIELD_BRAND = "brand";
    public static final String CAR_FIELD_CATEGORY = "category";
    public static final String CAR_FIELD_CATEGORY_NAME = "name";
    public static final String CAR_FIELD_YEAR = "year";
    public static final String CAR_FIELD_PRICE = "price";
    public static final String REVIEW_FIELD_TEXT = "text";
    public static final String REVIEW_FIELD_RATING = "rating";

    public static final String QUERY_LIKE = "%%%s%%";

    //exception message
    public static final String CAR_NOT_EXISTS = "Car %s not exists by client id %s";
    public static final String CAR_BAD_REQUEST = "Bad Car request";
    public static final String CAR_NOT_FOUND = "Car not found by id %s";
    public static final String CAR_SHOWROOM_BAD_REQUEST = "Bad Car showroom request";
    public static final String CAR_SHOWROOM_NOT_FOUND = "Car showroom not found by id %s";
    public static final String CATEGORY_BAD_REQUEST = "Bad category request";
    public static final String CATEGORY_NOT_FOUND = "Category not found by id %s";
    public static final String CLIENT_BAD_REQUEST = "Bad client request";
    public static final String CLIENT_NOT_FOUND = "Client not found by id %s";
    public static final String REVIEW_BAD_REQUEST = "Bad review request";
    public static final String REVIEW_NOT_FOUND = "Review not found by id %s";


}
