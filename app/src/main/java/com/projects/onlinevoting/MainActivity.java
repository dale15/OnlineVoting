package com.projects.onlinevoting;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.projects.onlinevoting.APIs.VotingAPI;
import com.projects.onlinevoting.Models.MemberModel;
import com.projects.onlinevoting.Utilities.RetrofitServiceGenerator;
import com.projects.onlinevoting.Utilities.SavePrefUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    LinearLayout img_enter;
    EditText et_name;
    TextView appTitle, name1, name2;

    Typeface font;
    VotingAPI votingAPI;
    boolean is_voted = false;

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

        SavePrefUtil.saveBool("is_voted", is_voted, this);
        votingAPI = RetrofitServiceGenerator.createService(VotingAPI.class);

        img_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<MemberModel> getMemberDetail = votingAPI.getMembers(et_name.getText().toString());
                getMemberDetail.enqueue(new Callback<MemberModel>() {
                    @Override
                    public void onResponse(Call<MemberModel> call, Response<MemberModel> response) {
                        if(response.isSuccessful()) {
                            if(response.body().getStatus().equals("200")) {
                                Intent intent = new Intent(MainActivity.this, VoteMainActivity.class);
                                startActivity(intent);
                            } else {
                                final AlertDialog.Builder builder;
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                                } else {
                                    builder = new AlertDialog.Builder(MainActivity.this);
                                }
                                builder.setTitle("Member Confirmation")
                                        .setMessage(response.body().getMessage() + ", Please try again.")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        })
                                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        })
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "An error occured, Please try again", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MemberModel> call, Throwable t) {
                        Log.d("onError", t.getLocalizedMessage());
                    }
                });
            }
        });
    }

    public void setTypeFace() {
        font = Typeface.createFromAsset(getAssets(), "Garamond.ttf");
        appTitle.setTypeface(font);
        name1.setTypeface(font);
        name2.setTypeface(font);
    }

}
