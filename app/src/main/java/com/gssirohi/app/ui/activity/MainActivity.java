package com.gssirohi.app.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gssirohi.app.R;
import com.gssirohi.app.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.tv_welcome);
        Button b = (Button) findViewById(R.id.b_continue);
        tv.setText("Welcome, next screen will show you marvel characters list.");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleContnueClick();
            }
        });
    }

    private void handleContnueClick() {
        getNavigator().navigateToCharactersListActivity();
    }
}
