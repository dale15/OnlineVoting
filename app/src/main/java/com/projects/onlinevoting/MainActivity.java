package com.projects.onlinevoting;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LinearLayout img_enter;
    EditText et_name;
    TextView appTitle, name1, name2;

    Typeface font;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_enter = (LinearLayout) findViewById(R.id.img_enter);
        et_name = (EditText) findViewById(R.id.et_name);
        appTitle = (TextView) findViewById(R.id.txt_apptitle);
        name1 = (TextView) findViewById(R.id.txt_name);
        name2 = (TextView) findViewById(R.id.txt_name2);

        // Initialize
        setTypeFace();



    }

    public void setTypeFace() {

        font = Typeface.createFromAsset(getAssets(), "Garamond.ttf");
        appTitle.setTypeface(font);
        name1.setTypeface(font);
        name2.setTypeface(font);

    }

}
