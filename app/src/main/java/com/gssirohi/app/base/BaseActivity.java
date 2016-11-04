package com.gssirohi.app.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gssirohi.app.utility.AppNavigator;

public abstract class BaseActivity extends AppCompatActivity {

    private AppNavigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigator = new AppNavigator(this);
    }

    public AppNavigator getNavigator() {
        return navigator;
    }


}
