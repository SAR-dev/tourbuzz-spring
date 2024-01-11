package com.backend.api;

public class Constants {
    public static final String TITLE_EXISTS = "Title already exists.";
    public static final String SLUG_EXISTS = "Slug already exists.";
    public static final String NAME_EXISTS = "Name already exists.";
    public static final String USERNAME_EXISTS = "Username already exists.";
    public static final String EMAIL_EXISTS = "Email already exists.";
    public static final String UPDATE_FAILED = "Update failed";
    public static final String DELETE_FAILED = "Delete failed";
    public static final String DATE_FORMAT_ERROR = "Check the date format of input fields";

    public static final Integer TOUR_DATE_AFTER_MIN = 2;
    public static final String TOUR_DATE_LESS_THAN_MIN_DATE = "Tour start date must be after " + (TOUR_DATE_AFTER_MIN + 1 )+" days from current date";
    public static final String TOUR_FINISH_SHOULD_BE_AFTER_START = "Tour finish date must be after start date";
    public static final String TOUR_LAST_SELL_SHOULD_BE_BEFORE_START = "Tour last sell date must be before start date";
    public static final String NON_UNIQUE_TOUR_LOG_DATE = "Tour logs dates are not unique";
    public static final String TOUR_LOG_SHOULD_BE_BETWEEN_START_END = "Tour logs date should be between tour start and finish date";

    public static final String LOGIN_INVALID_LINK = "Invalid link.";
    public static final String LOGIN_EXPIRED_LINK = "Expired link.";
    public static final String LOGIN_INVALID_TOKEN = "Invalid token.";
    public static final String LOGIN_EXPIRED_TOKEN = "Token expired.";

    public static final String DD_MM_YYYY_REGEX = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$";
    public static final String DD_MM_YYYY_FORMAT = "dd/MM/yyyy";

    public static final String DD_MM_YYYY_HH_MM_REGEX = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4} ([01]\\d|2[0-3]):([0-5]\\d)$";
    public static final String DD_MM_YYYY_HH_MM_FORMAT = "dd/MM/yyyy HH:mm";

    public static final String ADMIN_HEADER_NAME = "x-api-key";
    public static final String ACCESS_HEADER_NAME = "Authorization";
}

