package org.techtown.autocalendar3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    MonthFragment monthFragment;
    WeekFragment weekFragment;
    TodayFragment todayFragment;
    AddFragment addFragment;
    AddJobFragment addJobFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        monthFragment = new MonthFragment();
        weekFragment = new WeekFragment();
        todayFragment = new TodayFragment();
        addFragment = new AddFragment();
        addJobFragment = new AddJobFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, monthFragment).commit();

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.button_month:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, monthFragment).commit();
                        return true;
                    case R.id.button_week:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, weekFragment).commit();
                        return true;
                    case R.id.button_today:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, todayFragment).commit();
                        return true;
                    case R.id.button_add_schedule:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, addFragment).commit();
                        return true;
                    case R.id.button_add_job:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, addJobFragment).commit();
                        return true;
                }
                return false;
            }
        });

    }

}
