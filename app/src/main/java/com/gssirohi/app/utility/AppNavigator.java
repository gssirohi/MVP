package com.gssirohi.app.utility;

import android.content.Context;

import com.gssirohi.app.ui.activity.CharacterListActivity;


/**
 * Created by gssirohi on 3/7/16.
 */
public class AppNavigator {
    private Context context;

    private AppNavigator() {
        //default constructor blocked
    }

    public AppNavigator(Context context) {
        this.context = context;
    }

    public void navigateToCharactersListActivity() {
        if (context != null) {
            context.startActivity(CharacterListActivity.getCallingIntent(context));
        }
    }
}

