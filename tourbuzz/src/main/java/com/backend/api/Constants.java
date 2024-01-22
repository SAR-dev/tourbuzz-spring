package com.backend.api;

public class Constants {
    public static class HEADER {
        public static final String ADMIN_HEADER_NAME = "x-api-key";
        public static final String ACCESS_HEADER_NAME = "Authorization";
    }

    public static class DATES {
        public static final String DD_MM_YYYY_REGEX = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$";
        public static final String DD_MM_YYYY_FORMAT = "dd/MM/yyyy";
        public static final String DD_MM_YYYY_HH_MM_REGEX = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4} ([01]\\d|2[0-3]):([0-5]\\d)$";
        public static final String DD_MM_YYYY_HH_MM_FORMAT = "dd/MM/yyyy HH:mm";
    }

    public static class JWT {
        public static final String MAGIC_TOKEN_SECRET_KEY = "7zy#&i=ft5q7r3uooj=r-bo2ffi&nhdq_trg_h23z8r2qw4^";
        public static final Integer MAGIC_TOKEN_EXPIRE_AFTER = 60*30;
        public static final String ACCESS_TOKEN_SECRET_KEY = "d7k*n2iz7!+lt58=n(2hz3*fh+ttrv9th(qw_5)*d_i67tzu";
        public static final Integer ACCESS_TOKEN_EXPIRE_AFTER = 60*60*24;
        public static final Integer ADMIN_KEY_EXPIRE_AFTER_IN_SEC = 60*5;
        public static final Integer ADMIN_TOKEN_EXPIRE_AFTER_IN_SEC = 60*60*5;
    }

    public static class IMGBB {
        public static final String API_KEY = "613ee68f6bc89f194d175614a4ca500f";
        public static final String BASE_URL = "https://api.imgbb.com/1/upload";
    }

    public static final String AVATAR_GENERATOR_BASE_URL = "https://api.dicebear.com/7.x/adventurer-neutral/svg?seed=";
    public static final Integer TOUR_DATE_AFTER_MIN = 2;
    public static final String FRONTEND_URL = "http://localhost:300";
}
