/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */
package main.habitivity.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import main.habitivity.R;
import main.habitivity.controllers.HabitListController;
import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.HabitSingletonContainer;
import main.habitivity.users.User;
import main.habitivity.users.UserContainer;

public class JustHabitDetails extends BaseActivity {

    private static String TAG = "JustHabitDetails";//testing
    private TextView habitName;
    private TextView reason;
    private TextView startDate;
    private Habit curHabit;
    private TextView dayToOccur;
    private User loggedInUser;
    private Date today = new Date();
    private ArrayList<Date> skipDay = new ArrayList<>();
    private Integer completedOnSched = 0;
    private final String[] statusNames = {"Completed", "Uncompleted"};
    /**
     * import added to gradle MPAndroid chart by PhilJay
     * https://github.com/PhilJay/MPAndroidChart
     * For visualizing the count of all the scheduled days of the week passed and those done on time
     * via pie chart.
     */
    PieChart statusChart;
    private HabitListController habitListController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_just_habit_details);
       resolveDependencies();

       curHabit = HabitSingletonContainer.getInstance().getHabit();
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
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy", Locale.CANADA);
        String startingDate = "Starting Date: " + df.format(curHabit.getStartDate());
        startDate.setText(startingDate);

        Log.d(TAG, "StartChart"); //Checking

        //Creating the pie chart from the Habit's 'data/events'
        statusChart = (PieChart) findViewById(R.id.graph);
        statusChart.setDescription(null);
        statusChart.setRotationEnabled(false);
        statusChart.setUsePercentValues(true);
        statusChart.setHoleRadius(10f);
        statusChart.setTransparentCircleAlpha(0);
        statusChart.setCenterText("On Time and Missed Events");
        statusChart.setCenterTextSize(0);
        //statusChart.setDrawEntryLabels(true);
        //statusChart.setEntryLabelTextSize(10);

        //Setup data
        loggedInUser = UserContainer.getInstance().getUser();
        for(HabitEvent event: loggedInUser.getHabitEvents() ){
            if (event.getId().equals(curHabit.getId()) && event.getOnSched()){
                skipDay.add(event.getCompletionDate());
                completedOnSched += 1;
            }
        }
        if ( loggedInUser.getLastLogin() != null ) {
            curHabit.addPassedDayCount(loggedInUser.getLastLogin(), today, false, skipDay);
        }
        System.out.print(completedOnSched);
        System.out.print(completedOnSched);
        addDataSet(completedOnSched/*curHabit.getOnSchedCount()*/, curHabit.getPassedDayCount() /*+ curHabit.getFakeAddDays()*/);

    }

    /**
     * Visualizes the count of all the scheduled days of the week passed and those done on time via
     * pie chart.
     *
     * @author Nicolas Parada
     * @version 1.0
     * @since 1.0
     */
    private void addDataSet(int completed, int uncompleted) {
        Log.d(TAG, "data Chart" + completed + " " + uncompleted); //Checking
        ArrayList<PieEntry> sEntries = new ArrayList<>();
        ArrayList<String> sNames = new ArrayList<>();
        int[] statusEntries = {completed, uncompleted}; /*TESTING: statusEntries[0] = 15; statusEntries[1] = 25;*/

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

    }

    private void resolveDependencies() {
        HabitApplication app = getHabitApplication();
        habitListController = app.getHabitListController();
    }

    public void onAdd(View view) {
        Intent intent = new Intent(getApplicationContext(), AddEvent.class);
        startActivity(intent);
    }

    public void onEdit(View view) {
        Intent intent = new Intent(getApplicationContext(), EditHabit.class);
        startActivity(intent);
    }

    public void onDelete(View view) {
        Intent intent = new Intent(getApplicationContext(), HabitivityMain.class);
        habitListController.removeHabit(curHabit);
        startActivity(intent);
    }
}
