package main.habitivity;


import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import main.habitivity.activities.HabitivityMain;
import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.HabitRepository;
import main.habitivity.services.AndroidFileHandler;
import main.habitivity.services.ConnectivityService;
import main.habitivity.services.LocalHabitService;
import main.habitivity.services.WhichHabitService;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HabitRepositoryTests{
    HabitRepository habitRepository;
    WhichHabitService habitService;
    @Before
    public void set_up() {
        AndroidFileHandler fileHandler = mock(AndroidFileHandler.class);
        ConnectivityService connectService = mock(ConnectivityService.class);
        WhichHabitService habitService = mock(WhichHabitService.class);
        habitRepository = new HabitRepository(habitService);
    }

    @Test
    public void test_habitRepoCreation(){
        assertTrue(habitRepository != null);
    }

    @Test
    public void test_habitUserId(){
        habitRepository.setUserID("Shally");
        assertEquals(habitRepository.getUserID(), "Shally");
    }

    @Test
    public void test_add_removeHabits(){
        WhichHabitService habitService = mock(WhichHabitService.class);
        HabitRepository habitRepository = new HabitRepository(habitService);
        assertEquals(habitRepository.getHabits().size(), 0);

        Habit habit = new Habit();
        habit.setTitle("test habit");
        habitRepository.addHabit(habit);
        verify(habitService).addHabitRequest(habit);
        //reset for next tests since the test cases don't necessarily run in order
        habitRepository.removeHabit(habit);
        verify(habitService).deleteHabitRequest(habit);
    }

    @Test
    public void test_updateHabits(){
        WhichHabitService habitService = mock(WhichHabitService.class);
        HabitRepository habitRepository = new HabitRepository(habitService);
        assertEquals(habitRepository.getHabits().size(), 0);

        Habit habit = new Habit();
        habit.setTitle("test habit");
        Habit newHabit = new Habit();
        newHabit.setTitle("ii");
        habitRepository.addHabit(habit);
        habitRepository.updateHabit(habit, newHabit);
        verify(habitService).updateHabitRequest(habit, newHabit);
    }

    @Test
    public void test_add_removeHabitEvents(){
        WhichHabitService habitService = mock(WhichHabitService.class);
        HabitRepository habitRepository = new HabitRepository(habitService);
        assertEquals(habitRepository.getHabitEvents().size(), 0);

        HabitEvent habitEvent = new HabitEvent(new Date());
        habitEvent.setComment("test habitEvent");
        habitRepository.addHabitEvent(habitEvent);
        verify(habitService).addHabitEventRequest(habitEvent);
        //reset for next tests since the test cases don't necessarily run in order
        habitRepository.removeHabitEvent(habitEvent);
        verify(habitService).deleteHabitEventRequest(habitEvent);
    }

    @Test
    public void test_updateHabitEvent(){
        WhichHabitService habitService = mock(WhichHabitService.class);
        HabitRepository habitRepository = new HabitRepository(habitService);
        assertEquals(habitRepository.getHabits().size(), 0);

        HabitEvent habitEvent = new HabitEvent(new Date());
        habitEvent.setComment("test habit");
        HabitEvent newHabitEvent = new HabitEvent(new Date());
        newHabitEvent.setComment("ii");
        habitRepository.addHabitEvent(habitEvent);
        habitRepository.updateHabitEvent(habitEvent, newHabitEvent);
        verify(habitService).updateHabitEventRequest(habitEvent, newHabitEvent);
    }

}
