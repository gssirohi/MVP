package com.gssirohi.app.constant;

/**
 * Created by gssirohi on 5/7/16.
 */
public enum UseCases {
    FETCH_PRODUCT_LIST("FETCH_PRODUCT_LIST", 1),
    FETCH_PRODUCT_DETAILS("FETCH_PRODUCT_DETAILS", 2),
    FETCH_PRODUCT_IMAGE("FETCH_PRODUCT_IMAGE", 3);


    String name;
    int code;

    private UseCases(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
