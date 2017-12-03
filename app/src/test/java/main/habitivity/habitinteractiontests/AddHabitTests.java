package main.habitivity.habitinteractiontests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import main.habitivity.habits.IHabitRepository;
import main.habitivity.interactions.AddHabit;
import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitRepository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AddHabitTests {
    private IHabitRepository habitRepository;
    private AddHabit addHabit;

    @Before
    public void set_up() {
        habitRepository = mock(IHabitRepository.class);
        addHabit = new AddHabit(habitRepository);
    }

//    @Test
//    public void test_add_thenAddIsCalledInRepository() {
//        String newHabitName = "New Habit";
//        Date newHabitStartDate = new Date(0);
//        List<Integer> newHabitDaysToComplete = Arrays.asList(Calendar.MONDAY, Calendar.THURSDAY);
//
//        addHabit.add(newHabitName, newHabitStartDate, newHabitDaysToComplete);
//
//        ArgumentCaptor<Habit> habitCaptor = ArgumentCaptor.forClass(Habit.class);
//        verify(habitRepository).addHabit(habitCaptor.capture());
//
//        Habit createdHabit = habitCaptor.getValue();
//
//        assertEquals(newHabitName, createdHabit.getTitle());
//        assertEquals(newHabitStartDate, createdHabit.getStartDate());
//        assertEquals(newHabitDaysToComplete, createdHabit.getDaysOfTheWeekToComplete());
//        assertEquals(new ArrayList<>(), createdHabit.getCompletedEvents());
//    }

}
