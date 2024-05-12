package com.backend.api;

import org.springframework.beans.factory.annotation.Value;

public class Constants {
    @Value("${server.url}")
    public static String BACKEND_BASE_URL;

    @Value("${client.url}")
    public static String FRONTEND_BASE_URL;

    public static class HEADER {
        public static final String ADMIN_HEADER_NAME = "x-api-key";
        public static final String ACCESS_HEADER_NAME = "Authorization";
        public static final String ACCESS_HEADER_STARTS_WITH = "Bearer ";
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
    public static final Integer MAX_COMMENT_COUNT_IN_5_MIN = 5;

    public static class TEMPLATES {
        public static final String VARIABLE_NAME = "data";
        public static final String MAGIC_LINK = "magic-link.html";
        public static final String PAYMENT_SUCCESS = "payment-success.html";
        public static final String PAYMENT_FAILED = "payment-failed.html";
    }

    public static class BKASH {
        public static final String API_KEY = "4f6o0cjiki2rfm34kfdadl1eqq";
        public static final String API_SECRET = "2is7hdktrekvrbljjh44ll3d9l1dtjo4pasmjvs5vl5qr3fug4b";
        public static final String USERNAME = "sandboxTokenizedUser02";
        public static final String PASSWORD = "sandboxTokenizedUser02@12345";
        public static final String CREATE_PAYMNET_API = "https://tokenized.sandbox.bka.sh/v1.2.0-beta/tokenized/checkout/create";
        public static final String EXECUTE_PAYMENT_API = "https://tokenized.sandbox.bka.sh/v1.2.0-beta/tokenized/checkout/execute";
        public static final String GRANT_TOKEN_API = "https://tokenized.sandbox.bka.sh/v1.2.0-beta/tokenized/checkout/token/grant";
        public static final String QUERY_PAYMENT_API = "https://tokenized.sandbox.bka.sh/v1.2.0-beta/tokenized/checkout/payment/status";
        public static final String REFRESH_TOKEN_API = "https://tokenized.sandbox.bka.sh/v1.2.0-beta/tokenized/checkout/token/refresh";
        public static final String REFUND_API = "https://tokenized.sandbox.bka.sh/v1.2.0-beta/tokenized/checkout/payment/refund";
        public static final String REFUND_STATUS_API = "https://tokenized.sandbox.bka.sh/v1.2.0-beta/tokenized/checkout/payment/refund";
        public static final String SEARCH_TRANSACTION_API = "https://tokenized.sandbox.bka.sh/v1.2.0-beta/tokenized/checkout/general/searchTransaction";
        public static final String DEFAULT_MODE = "0011";
        public static final String DEFAULT_CURRENCY = "BDT";
        public static final String DEFAULT_INTENT = "sale";
        public static final String DEFAULT_CALLBACK_URL = BACKEND_BASE_URL + "/bkash/callback";
        public static final String PAYMENT_NOT_COMPLETED_CODE = "0000";
    }

    public static class MAGIC_LINK {
        public static final String TOKEN_VERIFY_URL = FRONTEND_BASE_URL + "/verify/";
        public static final String MAIL_HEADER = "TourBuzz Authentication";

    }
}
