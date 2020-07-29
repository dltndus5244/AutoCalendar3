package org.techtown.autocalendar3;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class AddFragment extends Fragment {

    private Button add_schedule_button;
    private EditText input_schedule_title;
    private EditText input_schedule_start_time;
    private EditText input_schedule_end_time;

    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            start_updateLabel();
        }
    };

    Calendar myCalendar2 = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener myDatePicker2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar2.set(Calendar.YEAR, year);
            myCalendar2.set(Calendar.MONTH, month);
            myCalendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            end_updateLabel();
        }
    };

    private void start_updateLabel() {
        String myFormat = "yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

        EditText et_date = (EditText) getView().findViewById(R.id.start_date);
        et_date.setText(sdf.format(myCalendar.getTime()));
    }

    private void end_updateLabel() {
        String myFormat = "yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

        EditText end_date = (EditText) getView().findViewById(R.id.end_date);
        end_date.setText(sdf.format(myCalendar2.getTime()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_add, container, false);

        input_schedule_title = view.findViewById(R.id.input_schedule_title);
        input_schedule_start_time = view.findViewById(R.id.start_time);
        input_schedule_end_time = view.findViewById(R.id.end_time);

        add_schedule_button = view.findViewById(R.id.add_schedule_button);
        add_schedule_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("schedule_title", input_schedule_title.getText().toString());
                bundle.putString("schedule_start_time", input_schedule_start_time.getText().toString());
                bundle.putString("schedule_end_time", input_schedule_end_time.getText().toString());

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MonthFragment monthFragment = new MonthFragment();
                monthFragment.setArguments(bundle);
                transaction.replace(R.id.container, monthFragment);
                transaction.commit();
                input_schedule_title.setText(null);
            }
        });



        EditText et_date = (EditText)view.findViewById(R.id.start_date);

        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = simpleDate.format(mDate);
        et_date.setText(getTime);

        et_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), myDatePicker, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        EditText end_date = (EditText)view.findViewById(R.id.end_date);

        end_date.setText(getTime);

        end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), myDatePicker2, myCalendar2.get(Calendar.YEAR),
                        myCalendar2.get(Calendar.MONTH), myCalendar2.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        final EditText et_time = (EditText)view.findViewById(R.id.start_time);
        et_time.setText("PM 1시 0분");
        et_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String state = "AM";
                        if (selectedHour > 12) {
                            selectedHour -= 12;
                            state = "PM";
                        }
                        et_time.setText(state + " " + selectedHour + "시 " + selectedMinute + "분");
                    }
                }, hour, minute, false);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        final EditText end_time = (EditText)view.findViewById(R.id.end_time);
        end_time.setText("PM 2시 0분");
        end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String state = "AM";
                        if (selectedHour > 12) {
                            selectedHour -= 12;
                            state = "PM";
                        }
                        end_time.setText(state + " " + selectedHour + "시 " + selectedMinute + "분");
                    }
                }, hour, minute, false);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });


        return view;
    }

}
