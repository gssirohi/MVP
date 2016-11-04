package com.gssirohi.app.constant;

/**
 * Created by gssirohi on 28/8/16.
 */
public class AppConstants {

    public static final int MAX_CONNECTION_TIMEOUT = 120000;
    public static final boolean DEBUG_MODE = true;

    // url to point to can be changed based on build type
    public static String SUFFIX_URL = "/v1/public";
    public static String PREFIX_URL = "http://";
    public static String SECURE_PREFIX_URL = "https://";
    public static final String SECURE_BASE_URL = SECURE_PREFIX_URL + "gateway.marvel.com" + SUFFIX_URL;
    public static final String BASE_URL = PREFIX_URL + "gateway.marvel.com" + SUFFIX_URL;

    public static String MARVEL_PUBLIC_KEY = "a87a28b9025043a8ce42192981e23140";
    public static String MARVEL_PRIVATE_KEY = "c53390fbc8d764e012bdb4f17ff3c5140948a438";
    public static String MARVEL_TS = "1";
    public static String MARVEL_MD5_HASH = "b595807c6671e96613bbf9f81d9bf58a";

}
