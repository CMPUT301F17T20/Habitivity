package main.habitivity.habits;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by N on 11/5/2017.
 */

public class TodayHabitRepository /**Instead Extend HabitRepository placing habits for this weekday in a new ArrayList of Habit, only get and set them**/ {
    private List<Habit> Today = new ArrayList<>();
    private Calendar weekDay;

    public TodayHabitRepository(ArrayList<Habit> habits /* Use HabitRepository?*/) {
        for (int i=0; i < habits.size(); i++) {
            /**
             * if a habit is for this day of the week add it to today's Habits
             */
            Habit habit = habits.get(i);
            if ( habit.checkDay(weekDay.get(Calendar.DAY_OF_WEEK)) ) {
                Today.add(habit);
            }
        }
    }

    /** Send changes to habits in todayHabitRepository to the main Habit Repository to update **/
}
