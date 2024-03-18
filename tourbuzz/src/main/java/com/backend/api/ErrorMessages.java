package com.backend.api;

public class ErrorMessages {
    public static final String TITLE_EXISTS = "Title already exists.";
    public static final String SLUG_EXISTS = "Slug already exists.";
    public static final String NAME_EXISTS = "Name already exists.";
    public static final String USERNAME_EXISTS = "Username already exists.";
    public static final String EMAIL_EXISTS = "Email already exists.";
    public static final String UPDATE_FAILED = "Update failed";
    public static final String DELETE_FAILED = "Delete failed";
    public static final String NOT_FOUND = "Not Found";
    public static final String DATE_FORMAT_ERROR = "Check the date format of input fields";
    public static final String TOUR_DATE_LESS_THAN_MIN_DATE = "Tour start date must be after " + (Constants.TOUR_DATE_AFTER_MIN + 1 )+" days from current date";
    public static final String TOUR_FINISH_SHOULD_BE_AFTER_START = "Tour finish date must be after start date";
    public static final String TOUR_LAST_SELL_SHOULD_BE_BEFORE_START = "Tour last sell date must be before start date";
    public static final String NON_UNIQUE_TOUR_LOG_DATE = "Tour logs dates are not unique";
    public static final String TOUR_LOG_SHOULD_BE_BETWEEN_START_END = "Tour logs date should be between tour start and finish date";
    public static final String NON_UNIQUE_TOUR_PACKAGE_TITLE = "Tour packages titles are not unique";
    public static final String LOGIN_INVALID_LINK = "Invalid link.";
    public static final String LOGIN_EXPIRED_LINK = "Expired link.";
    public static final String INVALID_TOKEN = "Invalid token.";
    public static final String EXPIRED_TOKEN = "Token expired.";
    public static final String MISSING_DATA = "Data missing from payloads.";
    public static final String SIGNIN_REQUIRED = "Please sign in first.";
    public static final String UNAUTHORIZED = "Not authorized";
    public static final String MAX_COMMENT_ERROR = "You have exceeded comment limit. Please try again later.";
    public static final String PAYMENT_NOT_COMPLETED = "Payment not completed.";
    public static final String PAYMENT_COMPLETED = "Payment not completed.";
}

