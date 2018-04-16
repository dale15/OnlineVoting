package com.projects.onlinevoting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout img_enter;
    EditText et_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_enter = (LinearLayout) findViewById(R.id.img_enter);
        et_name = (EditText) findViewById(R.id.et_name);



    }
}
