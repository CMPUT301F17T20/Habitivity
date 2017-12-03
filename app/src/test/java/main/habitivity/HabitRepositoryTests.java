package main.habitivity;


import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import main.habitivity.activities.HabitivityMain;
import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.HabitRepository;
import main.habitivity.services.AndroidFileHandler;
import main.habitivity.services.LocalHabitService;
import main.habitivity.services.WhichHabitService;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class HabitRepositoryTests{

//    @Test
//    public void testHabits() {
//        Context context = mock(Context.class);
//        AndroidFileHandler fileHandler = new AndroidFileHandler(context);
//        HabitRepository repo = new HabitRepository(new WhichHabitService(fileHandler));
//        Habit habit = new Habit();
//
//        repo.addHabit(habit);
//
//        assertEquals(repo.getHabit(habit.getId()), habit);
//        assertEquals(repo.getHabits().get(0), habit);
//
//        repo.updateHabit(repo.getHabit(habit.getId()));
//        assertEquals(repo.getHabit(habit.getId()), habit);
//
//
//        repo.removeHabit(habit.getId());
//
//        assertTrue(repo.getHabits().isEmpty());
//
//        //test for sorted habit events
//        HabitEvent e1 = new HabitEvent(new Date());
//        HabitEvent e2 = new HabitEvent(new Date());
//        HabitEvent e3 = new HabitEvent(new Date());
//
//        repo.addHabitEvent(e1);
//        repo.addHabitEvent(e3);
//        repo.addHabitEvent(e2);
//
//        ArrayList<HabitEvent> list = repo.getSortedEvents();
//
//        assertEquals(list.get(0), e3);
//    }
}
