package org.techtown.autocalendar3;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;

import android.widget.BaseAdapter;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;


public class MonthFragment extends Fragment {
    private TextView tv_frag2;
    private String result_title;
    private String result_start_time;
    private String result_end_time;
    private String result;

    private String result_job_title;
    private String result_job_date;
    private String result_job_time;

    private Context mContext;
    private ArrayList<String> array_schedule;
    private ListView mListView;
    private ScheduleAdapter mScheduleAdapter;



    String time,kcal,menu;

    private final oneDayDecorator OneDayDecorator = new oneDayDecorator();
    Cursor cursor;
    MaterialCalendarView materialCalendarView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_month, container, false);


        materialCalendarView = (MaterialCalendarView)view.findViewById(R.id.calendarView);

        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2020, 0, 1)) // 달력의 시작
                .setMaximumDate(CalendarDay.from(2030, 11, 31)) // 달력의 끝
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        materialCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                OneDayDecorator);

        mContext = getContext();
        mListView = (ListView)view.findViewById(R.id.list_schedule);


        if(getArguments() != null) {
            result_title = getArguments().getString("schedule_title");
            result_start_time = getArguments().getString("schedule_start_time");
            result_end_time = getArguments().getString("schedule_end_time");
            result = result_start_time + "~" + result_end_time + "   " + result_title;
        }

        array_schedule = new ArrayList<>();
        array_schedule.add(result);

        mScheduleAdapter = new ScheduleAdapter(mContext, array_schedule);
        mListView.setAdapter(mScheduleAdapter);

        return view;
    }

}