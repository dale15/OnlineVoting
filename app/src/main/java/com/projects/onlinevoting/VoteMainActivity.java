package com.projects.onlinevoting;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.projects.onlinevoting.APIs.VotingAPI;
import com.projects.onlinevoting.Adapter.CandidateAdapter;
import com.projects.onlinevoting.Models.CandidateModel;
import com.projects.onlinevoting.Utilities.RetrofitServiceGenerator;
import com.projects.onlinevoting.Utilities.SavePrefUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VoteMainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Menu menu;
    private Toolbar toolbar;

    public TextView nav_txtname, tv_countdown, tv_countdowntxt;
    public CircleImageView nav_prof;

    private RecyclerView mRecyclerView;

    private Calendar start_calendar = Calendar.getInstance();
    private Calendar end_calendar = Calendar.getInstance();

    VotingAPI votingAPI;

    private ArrayList<CandidateModel.CandInfoModel> mData = new ArrayList<CandidateModel.CandInfoModel>();

    boolean isVotingReady = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mRecyclerView = (RecyclerView) findViewById(R.id.main_recycler_content);
        tv_countdown = (TextView) findViewById(R.id.tv_countdown);
        tv_countdowntxt = (TextView) findViewById(R.id.tv_countdowntxt);


        votingAPI = RetrofitServiceGenerator.createService(VotingAPI.class);

        end_calendar.set(2018, 3, 23);
        long start_millis = start_calendar.getTimeInMillis(); //get the start time in milliseconds
        long end_millis = end_calendar.getTimeInMillis(); //get the end time in milliseconds
        long total_millis = (end_millis - start_millis); //total time in milliseconds

        CountDownTimer cdt = new CountDownTimer(total_millis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished);
                millisUntilFinished -= TimeUnit.DAYS.toMillis(days);

                long hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
                millisUntilFinished -= TimeUnit.HOURS.toMillis(hours);

                long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                millisUntilFinished -= TimeUnit.MINUTES.toMillis(minutes);

                long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);

                tv_countdown.setText(days + ":" + hours + ":" + minutes + ":" + seconds); //You can compute the millisUntilFinished on hours/minutes/seconds
            }

            @Override
            public void onFinish() {
                tv_countdown.setText("Voting is now ready, Please vote now");
                tv_countdowntxt.setVisibility(View.GONE);
            }
        };
        cdt.start();
        initNavigationDrawer();
        setCandidates();
    }

    private void setCandidates() {
        Call<CandidateModel> getAllCandidates = votingAPI.getCandidates();
        getAllCandidates.enqueue(new Callback<CandidateModel>() {
            @Override
            public void onResponse(Call<CandidateModel> call, Response<CandidateModel> response) {
                if(response.isSuccessful()) {
                    for(int x = 0; x < response.body().getResult().size(); x++) {
                        CandidateModel.CandInfoModel datas = new CandidateModel.CandInfoModel();
                        datas.setCandidate_name(response.body().getResult().get(x).getCandidate_name());
                        datas.setTotal_votes(response.body().getResult().get(x).getTotal_votes());
                        //datas.setCandidate_image(response.body().getResult().get(x).getCandidate_image());

                        mData.add(datas);
                    }
                    CandidateAdapter mAdapter = new CandidateAdapter(VoteMainActivity.this, mData, isVotingReady);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(VoteMainActivity.this, LinearLayoutManager.VERTICAL, false));
                    mRecyclerView.setAdapter(mAdapter);

                } else {
                    Toast.makeText(VoteMainActivity.this, "An error occured, Please try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CandidateModel> call, Throwable t) {

            }
        });
    }

    public void initNavigationDrawer() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        menu = navigationView.getMenu();

        View header = navigationView.getHeaderView(0);
        nav_txtname = (TextView) header.findViewById(R.id.nav_txt);
        nav_prof = (CircleImageView) header.findViewById(R.id.profile_image);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                switch (id) {
                    case R.id.nav_home:
                        break;
                    case R.id.nav_voting_result:
                        break;

                }

                return false;
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View v) {
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(Gravity.START)) {
                    drawerLayout.closeDrawer(Gravity.START);
                } else {
                    drawerLayout.openDrawer(Gravity.START);
                }
            }
        });
    }
}
