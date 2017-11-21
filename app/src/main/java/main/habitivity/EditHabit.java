/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */
package main.habitivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import main.habitivity.controllers.AddHabitController;
import main.habitivity.controllers.AddHabitRequest;
import main.habitivity.controllers.UpdateHabitController;
import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitSingletonContainer;

public class EditHabit extends BaseActivity {

    private Button addDate;
    private TextView viewDate;
    private Date compDate;
    private Calendar cal = Calendar.getInstance();
    private EditText title;
    private Date startingDate = new Date();
    private UpdateHabitController updateHabitController;
    private EditText reason;
    private List<Integer> dayOfTheWeek = new ArrayList<Integer>();
    private Habit curHabit = HabitSingletonContainer.getInstance().getHabit();

    //should move these toggles into a separate class which will handle them. But this will do for now
    private ToggleButton monday;
    private ToggleButton tuesday;
    private ToggleButton wednesday;
    private ToggleButton thursday;
    private ToggleButton friday;
    private ToggleButton saturday;
    private ToggleButton sunday;

    private Button habitType;
    public static String habitTypeString = new String();

    private DatePickerDialog.OnDateSetListener addDateSetListen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_habit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        resolveDependencies();

        addDate = (Button) findViewById(R.id.chooseDate);
        viewDate = (TextView) findViewById(R.id.dateChoice);

        sunday = (ToggleButton) findViewById(R.id.Sun);
        monday = (ToggleButton) findViewById(R.id.Mon);
        tuesday = (ToggleButton) findViewById(R.id.Tues);
        wednesday = (ToggleButton) findViewById(R.id.Wed);
        thursday = (ToggleButton) findViewById(R.id.Thurs);
        friday = (ToggleButton) findViewById(R.id.Fri);
        saturday = (ToggleButton) findViewById(R.id.Sat);

        habitType = (Button) findViewById(R.id.colorButton);

        habitType.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                final CharSequence colors[] = new CharSequence[] {"red", "green", "blue", "black"};

                AlertDialog.Builder builder = new AlertDialog.Builder(EditHabit.this);
                builder.setTitle("Pick a color to represent the habit type");
                builder.setItems(colors, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(colors[which] == "red") {
                            habitType.setBackgroundColor(Color.RED);
                            habitTypeString = "red";

                        }
                        else if(colors[which] == "green") {
                            habitType.setBackgroundColor(Color.GREEN);
                            habitTypeString = "green";
                        }
                        else if(colors[which] == "blue") {
                            habitType.setBackgroundColor(Color.BLUE);
                            habitTypeString = "blue";
                        }
                        else if(colors[which] == "black") {
                            habitType.setBackgroundColor(Color.BLACK);
                            habitTypeString = "black";
                        }
                    }
                });
                AlertDialog habitTypeChangedAlert = builder.create();
                habitTypeChangedAlert.show();
            }
        });

        //add the original values,
        title = (EditText) findViewById(R.id.habitInput);
        title.setText(curHabit.getTitle());
        reason = (EditText) findViewById(R.id.addComment);
        reason.setText(curHabit.getReason());
        habitTypeString = curHabit.getHabitType();

        if(habitTypeString == "red"){
            habitType.setBackgroundColor(Color.RED);
        }
        else if(habitTypeString == "green"){
            habitType.setBackgroundColor(Color.GREEN);
        }
        else if(habitTypeString == "black"){
            habitType.setBackgroundColor(Color.BLACK);
        }
        else if(habitTypeString == "blue"){
            habitType.setBackgroundColor(Color.BLUE);
        }

        for (Integer day: curHabit.getDaysOfTheWeekToComplete()){
            if(day==1){
                sunday.setChecked(true);
                dayOfTheWeek.add(new Integer(1));
            }
            else if(day==2){
                monday.setChecked(true);
                dayOfTheWeek.add(new Integer(2));
            }
            else if(day==3){
                tuesday.setChecked(true);
                dayOfTheWeek.add(new Integer(3));
            }
            else if(day==4){
                wednesday.setChecked(true);
                dayOfTheWeek.add(new Integer(4));
            }
            else if(day==5){
                thursday.setChecked(true);
                dayOfTheWeek.add(new Integer(5));
            }
            else if(day==6){
                friday.setChecked(true);
                dayOfTheWeek.add(new Integer(6));
            }
            else if(day==7){
                saturday.setChecked(true);
                dayOfTheWeek.add(new Integer(7));
            }
        }


        //SET LISTENERS////
        int yr = cal.get(Calendar.YEAR);
        int mth = cal.get(Calendar.MONTH);
        int dy = cal.get(Calendar.DAY_OF_MONTH);
        mth = mth + 1;
        String showDate = mth + "/" + dy + "/" + yr;
        viewDate.setText(showDate);

        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int yr = cal.get(Calendar.YEAR);
                int mth = cal.get(Calendar.MONTH);
                int dy = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dia = new DatePickerDialog(
                        EditHabit.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        addDateSetListen,
                        yr, mth, dy);
                dia.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dia.show();
            }
        });

        addDateSetListen = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                cal.set(year, month, day);
                compDate = cal.getTime();
                startingDate = compDate;
                month = month + 1;
                String showDate = month + "/" + day + "/" + year;
                viewDate.setText(showDate);
            }
        };

        saturday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Integer seven = new Integer(7);
                if(buttonView.isChecked()){
                    dayOfTheWeek.add(seven);}
                else{dayOfTheWeek.remove(seven);}
            }
        });
        sunday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Integer one = new Integer(1);
                if(buttonView.isChecked()){
                    dayOfTheWeek.add(one);}
                else{dayOfTheWeek.remove(one);}
            }
        });
        monday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Integer two = new Integer(2);
                if(buttonView.isChecked()){
                    dayOfTheWeek.add(two);}
                else{dayOfTheWeek.remove(two);}
            }
        });
        tuesday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Integer three = new Integer(3);
                if(buttonView.isChecked()){
                    dayOfTheWeek.add(three);}
                else{dayOfTheWeek.remove(three);}
            }
        });
        wednesday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Integer four = new Integer(4);
                if(buttonView.isChecked()){
                    dayOfTheWeek.add(four);}
                else{dayOfTheWeek.remove(four);}
            }
        });
        thursday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Integer five = new Integer(5);
                if(buttonView.isChecked()){
                    dayOfTheWeek.add(five);}
                else{dayOfTheWeek.remove(five);}
            }
        });
        friday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Integer six = new Integer(6);
                if(buttonView.isChecked()){
                    dayOfTheWeek.add(six);}
                else{dayOfTheWeek.remove(six);}
            }
        });
    }

    private void resolveDependencies() {
        HabitApplication app = getHabitApplication();
        updateHabitController = app.getUpdateHabitController();
    }

    public void onAdd(View view) {
        Intent intent = new Intent(getApplicationContext(), HabitListActivity.class);
        title = (EditText) findViewById(R.id.habitInput);
        reason = (EditText) findViewById(R.id.addComment);

        AddHabitRequest addHabitRequest = new AddHabitRequest();
        addHabitRequest.setId(title.getText().toString());
        addHabitRequest.setHabitType(habitTypeString);
        addHabitRequest.setStartDate(startingDate);
        addHabitRequest.setDaysOfTheWeek(dayOfTheWeek);
        addHabitRequest.setReason(reason.getText().toString());

        updateHabitController.updateHabit(addHabitRequest);
        startActivity(intent);
    }
}
