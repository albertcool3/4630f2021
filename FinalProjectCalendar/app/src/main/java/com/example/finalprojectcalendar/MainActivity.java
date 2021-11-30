package com.example.finalprojectcalendar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Navigation
    private BottomNavigationView bottomNav;

    //Monthly Calendar


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.DarkTheme);
        }
        else{
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new MonthView()).commit();

        //if savedinstance


    }




    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    //View view = bottomNav.findViewById(item.getItemId());
                    //view.performClick();

                    switch (item.getItemId()) {
                        case R.id.nav_month:
                            selectedFragment = new MonthView();
                            break;

                        case R.id.nav_week:
                            selectedFragment = new WeeklyView();
                            break;

                        case R.id.nav_events:
                            selectedFragment = new EventView();
                            break;

                        case R.id.nav_settings:
                            selectedFragment = new SettingsView();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.container, selectedFragment).commit();

                    return false;
                }
            };




}