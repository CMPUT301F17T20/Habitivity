package main.habitivity.activities;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import main.habitivity.R;
import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.HabitSingletonContainer;
import main.habitivity.users.User;
import main.habitivity.users.UserContainer;

public class EventDetailsNonEditable extends AppCompatActivity {
    private TextView habitName;
    private TextView reason;
    private TextView dayToOccur;
    private TextView startDate;
    private TextView habitCompletionDate;
    private TextView habitEventComment;
    private ImageView habitEventPhoto;
    private TextView habitEventTitle;

    public HabitEvent getMostRecentHabitEvent(User user, Habit curHabit){
        ArrayList<HabitEvent> habitEventList = user.getHabitEvents();
        ArrayList<HabitEvent> habitEventAssociatedWithHabit = new ArrayList<>();
        for(HabitEvent habitEvent: habitEventList){
            if(habitEvent.getId().equals(curHabit.getTitle())) {
                habitEventAssociatedWithHabit.add(habitEvent);
            }
        }
        Collections.sort(habitEventAssociatedWithHabit, new Comparator<HabitEvent>() {
            @Override
            public int compare(HabitEvent habitEvent, HabitEvent t1) {
                if (habitEvent.getCompletionDate() == null || t1.getCompletionDate() == null)
                    return 0;
                return habitEvent.getCompletionDate().compareTo(t1.getCompletionDate());
            }
        });

        //no habit events
        if(habitEventAssociatedWithHabit.size()==0){
            return null;
        }
        return habitEventAssociatedWithHabit.get(0);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details_non_editable);
        User user = UserContainer.getInstance().getUserToView();
        habitCompletionDate = (TextView)findViewById(R.id.latestCompletion);
        habitEventComment = (TextView)findViewById(R.id.comment);
        habitEventPhoto = (ImageView)findViewById(R.id.photo);
        habitEventTitle = (TextView)findViewById(R.id.HabitEvent);

        Habit curHabit = HabitSingletonContainer.getInstance().getHabit();
        HabitEvent recentHabitEvent = this.getMostRecentHabitEvent(user, curHabit);
        if(recentHabitEvent == null){
            habitEventTitle.setText("No Habit Events");
            habitCompletionDate.setText("Completion Date: None");
            habitEventComment.setText("Comment: None");
        }
        else {
            habitCompletionDate.setText("Completion Date: " + recentHabitEvent.getCompletionDate());
            habitEventComment.setText("Comment: " + recentHabitEvent.getComment());
            habitEventPhoto.setImageBitmap(recentHabitEvent.getPhoto());
        }

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
