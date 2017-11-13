/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */
package main.habitivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitRepository;

/**
 * This activity displays the habits scheduled for the current day
 *
 * @author Seth Bergen
 * @version 1.0
 * @see BaseActivity
 * @see Habit
 * @since 1.0
 */
public class TodaysHabits extends BaseActivity {

    private HabitListAdapter adapter;
    private ArrayList<Habit> habitList;
    private ArrayList<Habit> habitListToday;
    private HabitRepository repository;

    /**
     * Fetch habits from HabitRepository, filter for today's habits.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todays_habits);
        resolveDependencies();
        habitList = repository.getHabits();
        habitListToday = new ArrayList<Habit>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int today = calendar.get(Calendar.DAY_OF_WEEK);

        for (Habit habit : habitList){
            System.out.println(habit.getTitle()+"   Today is: "+today);
            for(Integer i : habit.getDaysOfTheWeekToComplete()){
                System.out.print(i);
            }
            System.out.println();
            if (habit.checkDay(today)){
                habitListToday.add(habit);
            }
        }

        adapter = new HabitListAdapter(this, R.layout.list_item_habit_today, habitListToday);
        ListView habitListDisplay = (ListView) findViewById(R.id.todays_habits);

        habitListDisplay.setAdapter(adapter);

        //setEnabled(false)

    }

    private void resolveDependencies() {
        HabitApplication app = getHabitApplication();
        repository = app.getHabitRepository();
    }

    /**
     * This class will allow us to customize the view for the habits in the list
     * Such customization could include any android UI element (TextView, Button, etc)
     *
     * @author Seth Bergen
     * @version 1.0
     * @see android.widget.ArrayAdapter
     * @see Habit
     * @since 1.0
     */
    private class HabitListAdapter extends ArrayAdapter<Habit>{
        private int layout;

        /**
         * Constructs HabitListAdapter objects
         *
         * @param context Target activity
         * @param resource Layout of each list item
         * @param objects List of objects to display
         */
        private HabitListAdapter(Context context, int resource, List<Habit> objects){
            super(context, resource, objects);
            layout = resource;
        }

        /**
         * Builds a view for the list item
         *
         * @param position Index of list item
         * @param convertView View used for each list item
         * @param parent Parent activity
         * @return convertView
         */
        @Override
        public View getView(final int position, View convertView, ViewGroup parent){
            HabitView mainHabitView = null;

            if (convertView == null){
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                HabitView habitView = new HabitView();

                habitView.habitDetails = (TextView) convertView.findViewById(R.id.textbox_habitDetails);

                convertView.setTag(habitView);
            }
            mainHabitView = (HabitView) convertView.getTag();
            mainHabitView.habitDetails.setText(habitListToday.get(position).toString());

            return convertView;
        }
    }

    /**
     * Holds the TextView and any additional UI elements for each Habit in habitList
     *
     * @author Seth Bergen
     * @version 1.0
     * @since 1.0
     */
    private class HabitView{
        TextView habitDetails;
    }
}
