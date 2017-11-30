package main.habitivity.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import main.habitivity.R;
import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitSingletonContainer;
import main.habitivity.users.User;
import main.habitivity.users.UserContainer;

public class EventDetailsNonEditable extends AppCompatActivity {
    private TextView habitName;
    private TextView reason;
    private TextView dayToOccur;
    private TextView startDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details_non_editable);
        User user = UserContainer.getInstance().getUserToView();

        Habit curHabit = HabitSingletonContainer.getInstance().getHabit();
        reason = (TextView) findViewById(R.id.reason);
        String stringToShow = "Reason: " + curHabit.getReason();
        reason.setText(stringToShow);

        habitName = (TextView) findViewById(R.id.habitName);
        habitName.setText(curHabit.getTitle());


        dayToOccur = (TextView) findViewById(R.id.days);
        String daysToOccur = "Occurs on: ";

        for (Integer day: curHabit.getDaysOfTheWeekToComplete()){
            if(day==2){
                daysToOccur = daysToOccur + "Monday, ";
            }
            else if(day==3){
                daysToOccur = daysToOccur + "Tuesday, ";
            }
            else if(day==4){
                daysToOccur = daysToOccur + "Wednesday, ";
            }
            else if(day==5){
                daysToOccur = daysToOccur + "Thursday, ";
            }
            else if(day==6){
                daysToOccur = daysToOccur + "Friday, ";
            }
            else if(day==7){
                daysToOccur = daysToOccur + "Saturday, ";
            }
            else if(day==1){
                daysToOccur = daysToOccur + "Sunday, ";
            }
        }
        dayToOccur.setText(daysToOccur);

        startDate = (TextView) findViewById(R.id.start);
        String startingDate = "Starting Date: " + curHabit.getStartDate().toString();
        startDate.setText(startingDate);

    }
}
