package com.gssirohi.app.utility;

import com.gssirohi.app.constant.AppAPIMethods;
import com.gssirohi.app.constant.Products;

/**
 * Created by gssirohi on 10/9/16.
 */
public class AppUtils {

    public static String getProductIdFromUri(String uri) {
        int lastSeparator = uri.lastIndexOf("/");
        int size = uri.length();
        String id = uri.substring(lastSeparator + 1, size);
        AppLogger.i(AppUtils.class, "productUri: " + uri + "    id:" + id);
        return id;
    }

    public static Products getProductTypeFromUri(String uri) {
        if (uri.contains(AppAPIMethods.FETCH_COMICS_LIST)) {
            return Products.COMIC;
        } else if (uri.contains(AppAPIMethods.FETCH_CHARACTERS_LIST)) {
            return Products.CHARACTER;
        } else if (uri.contains(AppAPIMethods.FETCH_EVENTS_LIST)) {
            return Products.EVENT;
        } else if (uri.contains(AppAPIMethods.FETCH_SERIES_LIST)) {
            return Products.SERIES;
        } else if (uri.contains(AppAPIMethods.FETCH_STORIES_LIST)) {
            return Products.STORY;
        } else if (uri.contains(AppAPIMethods.FETCH_CREATORS_LIST)) {
            return Products.CREATOR;
        }
        return null;
    }
}
