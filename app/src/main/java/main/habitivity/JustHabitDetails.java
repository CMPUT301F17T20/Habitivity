package main.habitivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitSingletonContainer;

public class JustHabitDetails extends BaseActivity {

    private TextView habitName;
    private TextView reason;
    private TextView startDate;
    private Habit curHabit;
    private TextView dayToOccur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just_habit_details);


       curHabit = HabitSingletonContainer.getInstance().getHabit();
       reason = (TextView) findViewById(R.id.reason);
       String stringToShow = "Reason: " + curHabit.getId();
       reason.setText(stringToShow);

       habitName = (TextView) findViewById(R.id.habitName);
       habitName.setText(curHabit.getTitle());


       dayToOccur = (TextView) findViewById(R.id.days);
       String daysToOccur = "Occurs on: ";

       for (Integer day: curHabit.getDaysOfTheWeekToComplete()){
           if(day==1){
               daysToOccur = daysToOccur + "Monday, ";
           }
           else if(day==2){
               daysToOccur = daysToOccur + "Tuesday, ";
           }
           else if(day==3){
               daysToOccur = daysToOccur + "Wednesday, ";
           }
           else if(day==4){
               daysToOccur = daysToOccur + "Thursday, ";
           }
           else if(day==5){
               daysToOccur = daysToOccur + "Friday, ";
           }
           else if(day==6){
               daysToOccur = daysToOccur + "Saturday, ";
           }
           else if(day==7){
               daysToOccur = daysToOccur + "Sunday, ";
           }
        }
        dayToOccur.setText(daysToOccur);

        startDate = (TextView) findViewById(R.id.start);
        String startingDate = "Starting Date: " + curHabit.getStartDate().toString();
        startDate.setText(startingDate);


    }


    public void onAdd(View view) {
        Intent intent = new Intent(getApplicationContext(), AddEvent.class);
        startActivity(intent);
    }
}
