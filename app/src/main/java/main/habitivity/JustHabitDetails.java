package main.habitivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import main.habitivity.controllers.AddHabitEventController;
import main.habitivity.controllers.AddHabitRequest;
import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitIDContainer;

public class JustHabitDetails extends BaseActivity {

    private TextView habitName;
    private TextView reason;
    private TextView startDate;
    private Habit curHabit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just_habit_details);


       curHabit = HabitIDContainer.getInstance().getHabit();
       reason = (TextView) findViewById(R.id.reason);
       String stringToShow = "Reason: " + curHabit.getId();
       reason.setText(stringToShow);
//
        habitName = (TextView) findViewById(R.id.habitName);
        habitName.setText(curHabit.getTitle());

        //startDate = (TextView) findViewById(R.id.start);
        //startDate.setText(curHabit.getStartDate().toString());


    }


    public void onAdd(View view) {
        Intent intent = new Intent(getApplicationContext(), AddEvent.class);
        startActivity(intent);
    }
}
