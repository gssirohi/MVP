package com.gssirohi.app.base;

import android.app.Application;


/**
 * Created by gssirohi on 3/7/16.
 */
public class BaseApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        AppCore.initialize(this);
    }

}
