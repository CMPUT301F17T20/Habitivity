package main.habitivity.activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import main.habitivity.R;
import main.habitivity.controllers.HabitListController;
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

    private static String TAG = "OnlyHabitDetails"; //testing
    private User loggedInUser;
    private Date today = new Date();
    private final String[] statusNames = {"Completed", "Uncompleted"};
    /**
     * import added to gradle MPAndroid chart by PhilJay
     * https://github.com/PhilJay/MPAndroidChart
     */
    PieChart statusChart;


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

        /**
         Log.d(TAG, "StartChart"); //Checking
         //Creating the pie chart from the Habit's 'data/events'
         statusChart = (PieChart) findViewById(R.id.graph);
         statusChart.setDescription(null);
         statusChart.setRotationEnabled(false);
         statusChart.setUsePercentValues(true);
         statusChart.setHoleRadius(10f);
         statusChart.setTransparentCircleAlpha(0);
         statusChart.setCenterText("On Time and Missed Events");
         statusChart.setCenterTextSize(10);
         //statusChart.setDrawEntryLabels(true);
         //statusChart.setEntryLabelTextSize(10);
         //Setup data
         if ( user.getLastLogin() != null ) {
         curHabit.addPassedDayCount(user.getLastLogin(), today, false);
         }
         addDataSet(curHabit.getOnSchedCount(), curHabit.getPassedDayCount() + curHabit.getFakeAddDays());
         */

    }

    /**
     private void addDataSet(int completed, int uncompleted) {
     Log.d(TAG, "data Chart" + completed + " " + uncompleted); //Checking
     ArrayList<PieEntry> sEntries = new ArrayList<>();
     ArrayList<String> sNames = new ArrayList<>();
     int[] statusEntries = {completed, uncompleted};
     for(int i = 0; i < statusEntries.length; i++) {
     sEntries.add(new PieEntry(statusEntries[i], i));
     }
     for(int i = 0; i < statusEntries.length; i++) {
     sNames.add(statusNames[i]);
     }
     //Creating data structure for entries
     PieDataSet statusSet = new PieDataSet(sEntries, "On Time and Missed Events");
     statusSet.setSliceSpace(2);
     statusSet.setValueTextSize(10);
     //colorize
     ArrayList<Integer> colors = new ArrayList<>();
     colors.add(Color.GREEN);
     colors.add(Color.RED);
     //colors.add(Color.GRAY);
     statusSet.setColors(colors);
     Legend mrWayne = statusChart.getLegend();
     mrWayne.setForm(Legend.LegendForm.NONE);
     //mrWayne.setPosition(Legend.LegendPosition.ABOVE_CHART_CENTER);
     //Add data to pie mmmmmmmm...
     PieData statusData = new PieData(statusSet);
     statusChart.setData(statusData);
     statusChart.invalidate();
     }*/
}