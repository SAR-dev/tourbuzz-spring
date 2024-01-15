package com.backend.api;

public class Constants {
    public static final Integer ADMIN_KEY_EXPIRE_AFTER_IN_SEC = 300;
    public static final Integer ADMIN_TOKEN_EXPIRE_AFTER_IN_SEC = 60*60;
    public static final String AVATAR_GENERATOR_BASE_URL = "https://api.dicebear.com/7.x/adventurer-neutral/svg?seed=";
    public static final String DD_MM_YYYY_REGEX = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$";
    public static final String DD_MM_YYYY_FORMAT = "dd/MM/yyyy";
    public static final String DD_MM_YYYY_HH_MM_REGEX = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4} ([01]\\d|2[0-3]):([0-5]\\d)$";
    public static final String DD_MM_YYYY_HH_MM_FORMAT = "dd/MM/yyyy HH:mm";
    public static final String ADMIN_HEADER_NAME = "x-api-key";
    public static final String ACCESS_HEADER_NAME = "Authorization";
    public static final Integer TOUR_DATE_AFTER_MIN = 2;
}
