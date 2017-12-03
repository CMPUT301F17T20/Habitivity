package main.habitivity;

import android.test.ActivityInstrumentationTestCase2;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import main.habitivity.activities.HabitivityMain;
import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.interactions.Clock;
import main.habitivity.interactions.IClock;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class HabitTest {

    @Before
    public void set_up() {
    }

    @Test
    public void test_titleChange(){
        Habit habit = new Habit();
        habit.setTitle("Habit");
        assertEquals("Habit", habit.getTitle());
    }

    @Test
    public void test_habitReason(){
        Habit habit = new Habit();
        habit.setReason("Habit");
        assertEquals("Habit", habit.getReason());
    }

    @Test
    public void test_habitStartDate(){
        IClock time = new Clock();
        Date newHabitDate = time.getCurrentDate();
        Habit habit = new Habit();
        habit.setStartDate(newHabitDate);
        assertEquals(newHabitDate, habit.getStartDate());
    }

    @Test
    public void test_habitReoccuringDays(){
        List<Integer> newHabitDays = Arrays.asList(Calendar.SUNDAY, Calendar.THURSDAY);
        Habit habit = new Habit();
        habit.setDaysOfTheWeekToComplete(newHabitDays);
        assertEquals(habit.getDaysOfTheWeekToComplete(), newHabitDays);
    }

    @Test
    public void test_habitType(){
        String newHabitType = "red";
        Habit habit = new Habit();
        habit.setHabitType(newHabitType);
        assertEquals(newHabitType, habit.getHabitType());
    }

    @Test
    public void test_userName(){
        Habit habit = new Habit();
        habit.setUserName("Shally");
        assertEquals("Shally", habit.getUserName());
    }

    @Test
    public void test_checkDay(){
        List<Integer> newHabitDays = Arrays.asList(Calendar.SUNDAY, Calendar.THURSDAY);
        Habit habit = new Habit();
        habit.setDaysOfTheWeekToComplete(newHabitDays);
        //Integer 5 indicates thursday
        assertTrue(habit.checkDay(5));
    }

    @Test
    public void test_completedEvents(){
        Habit habit = new Habit();
        assertTrue(habit.getCompletedEvents().size() == 0);
    }

    @Test
    public void test_checkSheduleCount(){
        Habit habit = new Habit();
        assertTrue(habit.getOnSchedCount() == 0);
    }
}
