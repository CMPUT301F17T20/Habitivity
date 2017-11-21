/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */
package main.habitivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import main.habitivity.controllers.HabitListController;
import main.habitivity.controllers.UpdateHabitController;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.HabitSingletonContainer;

public class HabitEventDetailsActivity extends BaseActivity {

    private DatePickerDialog.OnDateSetListener addDateSetListen;
    private Button addDate;
    private TextView viewDate;
    private TextView habitEventTitle;
    private TextView completionDate;
    private TextView comment;
    private HabitEvent curHabitEvent;
    private Calendar cal = Calendar.getInstance();
    private Date compDate = new Date();
    private UpdateHabitController updateHabitController;
    private HabitListController habitListController;

    public static Calendar toCalendar(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_event_details_new);
        resolveDependencies();

        habitEventTitle = (TextView) findViewById(R.id.habitEvent);
        completionDate = (TextView) findViewById(R.id.dateChoice);
        comment = (TextView) findViewById(R.id.addComment);

        curHabitEvent = HabitSingletonContainer.getInstance().getHabitEvent();

        String commentString = "Comment: " + curHabitEvent.getComment();
        comment.setText(commentString);
        habitEventTitle.setText(curHabitEvent.getId());

        addDate = (Button) findViewById(R.id.chooseDate);
        viewDate = (TextView) findViewById(R.id.dateChoice);

        viewDate.setText(curHabitEvent.getCompletionDate().toString());

        Calendar setDate = toCalendar(curHabitEvent.getCompletionDate());
        int yr = setDate.get(Calendar.YEAR);
        int mth = setDate.get(Calendar.MONTH);
        int dy = setDate.get(Calendar.DAY_OF_MONTH);

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
                        HabitEventDetailsActivity.this,
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
                int yr = cal.get(Calendar.YEAR);
                int mth = cal.get(Calendar.MONTH);
                int dy = cal.get(Calendar.DAY_OF_MONTH);
                compDate = cal.getTime();
                month = month + 1;
                String showDate = month + "/" + day + "/" + year;
                viewDate.setText(showDate);
            }
        };
    }

    private void resolveDependencies() {
        HabitApplication app = getHabitApplication();
        updateHabitController = app.getUpdateHabitController();
        habitListController = app.getHabitListController();
    }

    public void onEdit(View view){
        Intent intent = new Intent(getApplicationContext(), HabitivityMain.class);
        habitEventTitle = (TextView) findViewById(R.id.habitEvent);
        completionDate = (TextView) findViewById(R.id.dateChoice);
        comment = (TextView) findViewById(R.id.addComment);

        HabitEvent habitEvent = new HabitEvent(new Date());
        habitEvent.setId(habitEventTitle.getText().toString());
        habitEvent.setCompletionDate(this.compDate);
        habitEvent.setComment(comment.getText().toString());

        updateHabitController.updateHabitEvent(habitEvent);
        HabitSingletonContainer.getInstance().setHabitEvent(habitEvent);
        startActivity(intent);
    }

    public void onDelete(View view){
        Intent intent = new Intent(getApplicationContext(), HabitivityMain.class);
        habitListController.removeHabitEvent(HabitSingletonContainer.getInstance().getHabitEvent());
        startActivity(intent);
    }
}

