package main.habitivity;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import main.habitivity.activities.HabitivityMain;
import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by nparada on 10/23/17.
 */

public class HabitTest extends ActivityInstrumentationTestCase2 {
    private Habit habitWithoutCompletion;
    private Habit habitWithCompletion;
    private Habit habitnodays;
    private Habit habitsomedays;
    //private Habit notitle;
    private Habit atitle;
    //private Habit noReason;
    private Habit aReason;
    //private Habit noStartDate;
    private Habit aStartDate;
    private Habit noEvents;
    private Habit addsomeEvents;

    public HabitTest(){
        super(HabitivityMain.class);
    }

    public void set_up() {
        habitWithoutCompletion = new Habit();
        habitWithoutCompletion.setId("Habit-ID");
        habitWithoutCompletion.setTitle("AwesomeHabit");
        habitWithoutCompletion.setStartDate(new Date(1));
        habitWithoutCompletion.setDaysOfTheWeekToComplete(Arrays.asList(Calendar.SUNDAY));

        HabitEvent habitCompletion = new HabitEvent(new Date(1));
        habitCompletion.setId("Completion-ID");

        habitWithCompletion = new Habit();
        habitWithCompletion.setId("Habit-ID");
        habitWithCompletion.setTitle("AwesomeHabit");
        habitWithCompletion.setStartDate(new Date(1));
        habitWithCompletion.setDaysOfTheWeekToComplete(Arrays.asList(Calendar.SUNDAY));
        habitWithCompletion.setCompletedEvents(Arrays.asList(habitCompletion));
    }

    public void test_setHabitTitle_isSet_isGet(){
        String newHabitName = "New Habit";
        atitle.setTitle(newHabitName);
        assertEquals(newHabitName, atitle.getTitle());
    }

    public void test_setHabitReason_isSet_isGet(){
        String newHabitReason = "Habit Reason";
        aReason.setReason(newHabitReason);
        assertEquals(newHabitReason, aReason.getReason());
    }

    public void test_setHabitStartDate_isSet_isGet(){
        Date newHabitDate = new Date(1);
        aStartDate.setStartDate(newHabitDate);
        assertEquals(newHabitDate, aStartDate.getReason());
    }

    public void test_setHabitDays_isSet_isGet(){
        List<Integer> newHabitDays = Arrays.asList(Calendar.SUNDAY, Calendar.THURSDAY);
        habitsomedays.setDaysOfTheWeekToComplete(newHabitDays);
        assertEquals(newHabitDays, habitsomedays.getDaysOfTheWeekToComplete());

        List<Integer> noHabitDays = Arrays.asList();
        habitnodays.setDaysOfTheWeekToComplete(noHabitDays);
        assertEquals(noHabitDays, habitnodays.getDaysOfTheWeekToComplete());

        habitsomedays.setDaysOfTheWeekToComplete(noHabitDays);
        assertEquals(habitsomedays.getDaysOfTheWeekToComplete(), habitnodays.getDaysOfTheWeekToComplete());
    }

    public void test_setCompletedEvents_isSet_isGet(){
        HabitEvent newEvent = new HabitEvent(new Date(1));
        List<HabitEvent> newHabitEvents = new ArrayList<HabitEvent>();

        noEvents.setCompletedEvents(newHabitEvents);
        assertEquals(noEvents.getCompletedEvents(), newHabitEvents);

        newHabitEvents.add(newEvent);
        addsomeEvents.setCompletedEvents(newHabitEvents);
        assertEquals(addsomeEvents.getCompletedEvents(), newHabitEvents);
        assertEquals(addsomeEvents.getCompletedEvents().get(0), newEvent);

    }

    public void test_setHabitType_isSet_isGet(){
        String newHabitType = "New Type";
        atitle.setId(newHabitType);
        assertEquals(newHabitType, atitle.getId());
    }

    public void test_addDeleteHabitEventOrCompletedEvent_isSet_isGet(){
        HabitEvent newEvent = new HabitEvent(new Date(1));
        List<HabitEvent> newHabitEvents = new ArrayList<HabitEvent>();

        newHabitEvents.add(newEvent);
        addsomeEvents.setCompletedEvents(newHabitEvents);
        assertEquals(addsomeEvents.getCompletedEvents(), newHabitEvents);

        addsomeEvents.deleteHabitEvent(newEvent);
        assertTrue(addsomeEvents.getCompletedEvents().isEmpty());

//        addsomeEvents.addCompletedEvent(newEvent);
//        assertEquals(addsomeEvents.getCompletedEvents().get(0), newEvent);

        addsomeEvents.deleteHabitEvent(newEvent);
        assertTrue(addsomeEvents.getCompletedEvents().isEmpty());

        addsomeEvents.addHabitEvent(newEvent);
        assertEquals(addsomeEvents.getCompletedEvents().get(0), newEvent);

        addsomeEvents.deleteHabitEvent(newEvent);
        assertTrue(addsomeEvents.getCompletedEvents().isEmpty());


    }

    public void test_ifCompletedOnDayGetEventByDay_dayCompletedOn(){
        Date today = new Date(1);
        HabitEvent newEvent = new HabitEvent(today);
//        addsomeEvents.dayCompletedOn(today);
//        assertEquals(addsomeEvents.getCompletedEvents().get(0), newEvent);
    }
}
