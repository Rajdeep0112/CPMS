package com.release.cpmsmobileapp;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.net.ConnectivityManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.release.cpmsmobileapp.requestbody.RefreshTokenRequestBody;
import com.release.cpmsmobileapp.responsebody.DistrictResponse;
import com.release.cpmsmobileapp.responsebody.LoginResponse;
import com.release.cpmsmobileapp.utils.HelperUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    String token;
    public DrawerLayout drawerLayout;

    ApiInterface apiInterface;

    Toolbar toolbar;
    NavigationView navigationView;
    private long mBackPressed;
    private final long TIME_INTERVAL = 2000;
    private BottomNavigationView bottomNavigationView;

    private HelperUtils utils;
    private CPMSUser user;
    private Dialog dataConnection;
    private BroadcastReceiver connectionReceiver;

    @Override
    protected void onPause() {
        super.onPause();
        try {
            unregisterReceiver(connectionReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(connectionReceiver, filter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialisations();
        loadToken();
        showDashboard();
        configureToolbar();
        checkConnection();
    }

    private void refreshToken() {
        Call<LoginResponse> call = apiInterface.getRefreshToken(new RefreshTokenRequestBody(utils.getRefreshToken()));
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                LoginResponse model = response.body();
                if (model != null) {
                    utils.setAllUserDetails(model);
                    Toast.makeText(getApplicationContext(), utils.getName() + " logged in", Toast.LENGTH_SHORT).show();
                    recreate();

                } else {
                    utils.logout();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                utils.logout();
                finish();
            }
        });
    }

    private void checkTokenValidity() {
        if(utils.getToken().equals("")) {
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", "Bearer " + utils.getToken());
        Call<List<DistrictResponse>> call = apiInterface.getDistrictList(map);
        call.enqueue(new Callback<List<DistrictResponse>>() {
            @Override
            public void onResponse(Call<List<DistrictResponse>> call, Response<List<DistrictResponse>> response) {
                if (response.body() == null) {
                    refreshToken();
                }
            }

            @Override
            public void onFailure(Call<List<DistrictResponse>> call, Throwable t) {
                refreshToken();
            }
        });
    }


    private void configureBottomNavBar() {
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.dashboard) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, new DashboardFragment())
                        .commit();
                toolbar.setTitle("Dashboard");
            } else if (item.getItemId() == R.id.assigned_case) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, new AssignedCaseFragment(), "Cases")
                        .commit();
                toolbar.setTitle("Cases List");
            } else if (item.getItemId() == R.id.hearing_list) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, new HearingListFragment(), "Hearing List")
                        .commit();
                toolbar.setTitle("Hearing List");
            } else if (item.getItemId() == R.id.notice_list) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, new NoticeListFragment(), "Notice List")
                        .commit();
                toolbar.setTitle("Notice List");
            }
            return true;
        });
    }

    private void initialisations() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bottomNavigationView = findViewById(R.id.home_bottom_navigation);
        navigationView = findViewById(R.id.navigation_menu);
        drawerLayout = findViewById(R.id.my_drawer_layout);
        utils = new HelperUtils(MainActivity.this);
        user = new CPMSUser(MainActivity.this);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        token = utils.getToken();
    }

    private void configureNavigationDrawer() {
        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.user_logout) {
                configureAlertDialog();
            } else if (item.getItemId() == R.id.search_case) {
                startActivity(new Intent(this, SearchCaseActivity.class));
            }

            if (drawerLayout.isDrawerOpen(GravityCompat.START))
                drawerLayout.closeDrawer(GravityCompat.START);
            return false;
        });
    }

    public void getHeaderView(){
        View view = navigationView.getHeaderView(0);
        TextView name = view.findViewById(R.id.name);
        TextView rank = view.findViewById(R.id.rank);
        name.setText(user.getName());
        rank.setText(user.getOfficerRank());
    }

    private void configureAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Logout");
        alertDialog.setMessage("Do you want to log out?");

        alertDialog.setPositiveButton("Yes", (dialogInterface, i) -> {
                    utils.logout();
                    finish();
                })
                .setNegativeButton("Cancel", (dialogInterface, i) -> {
                });
        AlertDialog dialog = alertDialog.create();
        dialog.show();
    }

    private void loadToken() {
        token = utils.getToken();
        if (token.length() == 0) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void configureToolbar() {
        toolbar.setNavigationIcon(R.drawable.baseline_menu_24);
        toolbar.setNavigationOnClickListener(view -> drawerLayout.openDrawer(GravityCompat.START));
    }

    private void customBackButton() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                AssignedCaseFragment caseFragment = (AssignedCaseFragment) getSupportFragmentManager().findFragmentByTag("Cases");
                HearingListFragment hearingListFragment = (HearingListFragment) getSupportFragmentManager().findFragmentByTag("Hearing List");
                NoticeListFragment noticeListFragment = (NoticeListFragment) getSupportFragmentManager().findFragmentByTag("Notice List");
                if (caseFragment != null && caseFragment.isVisible()) {
                    showDashboard();
                } else if (hearingListFragment != null && hearingListFragment.isVisible()) {
                    showDashboard();
                } else if (noticeListFragment != null && noticeListFragment.isVisible()) {
                    showDashboard();
                } else {
                    if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
                        finish();
                    } else {
                        Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
                    }

                    mBackPressed = System.currentTimeMillis();
                }
            }
        };
        this.getOnBackPressedDispatcher().addCallback(this, callback);
    }

    private void showDashboard() {
        bottomNavigationView.setSelectedItemId(R.id.dashboard);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame, new DashboardFragment())
                .commit();
        toolbar.setTitle("Dashboard");
    }

    private void checkConnection(){
        connectionReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent != null && intent.getAction().equals(ConnectivityManager
                        .CONNECTIVITY_ACTION)) {

                    boolean isConnected = ConnectivityManagerCompat.getNetworkInfoFromBroadcast
                            ((ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE),
                                    intent).isConnected();

                    if (isConnected) {
                        onConnectionEstablished();
                    } else {
                        onConnectionAbsent(MainActivity.this);
                    }
                }
            }
        };
    }

    private void onConnectionEstablished(){
        if(dataConnection!=null && dataConnection.isShowing()){
            dataConnection.dismiss();
        }
        getHeaderView();
        checkTokenValidity();
        configureNavigationDrawer();
        configureBottomNavBar();
        customBackButton();
    }

    private void onConnectionAbsent(Context context){
        dataConnection = new Dialog(context);
        dataConnection.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dataConnection.setCanceledOnTouchOutside(false);
        dataConnection.setCancelable(false);

        dataConnection.setContentView(R.layout.data_connection);

        CardView connect = dataConnection.findViewById(R.id.connect);
        CardView cancel = dataConnection.findViewById(R.id.cancel);


        connect.setOnClickListener(v -> {
            startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
            dataConnection.dismiss();
        });

        cancel.setOnClickListener(v -> {
            dataConnection.dismiss();
            setResult(RESULT_CANCELED, null);
            finish();
        });

        dataConnection.show();
    }
}