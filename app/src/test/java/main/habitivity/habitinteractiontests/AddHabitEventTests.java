package main.habitivity.habitinteractiontests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.HabitRepository;
import main.habitivity.interactions.AddHabitEvent;
import main.habitivity.interactions.IClock;

import static org.hamcrest.core.IsInstanceOf.any;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AddHabitEventTests {
    private IClock clock;
    private HabitRepository habitRepository;
    private AddHabitEvent completeHabit;

    private Habit habitWithoutCompletion;
    private Habit habitWithCompletion;

    @Before
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

        clock = mock(IClock.class);
        habitRepository = mock(HabitRepository.class);
        completeHabit = new AddHabitEvent(habitRepository, clock);
    }

    @Test
    public void test_ifHabitExists_thenUpdateIsCalledWithCompletionAdded() {
        when(habitRepository.getHabit("Habit-ID")).thenReturn(habitWithoutCompletion);
        when(clock.getCurrentDate()).thenReturn(new Date(1));

        completeHabit.complete("Habit-ID");

        ArgumentCaptor<Habit> habitCaptor = ArgumentCaptor.forClass(Habit.class);
        verify(habitRepository).updateHabit(habitCaptor.capture());

        Habit habit = habitCaptor.getValue();

        HabitEvent expectedCompletion = habitWithCompletion.getCompletedEvents().get(0);
        HabitEvent actualCompletion = habit.getCompletedEvents().get(0);

        assertEquals(habit.getTitle(), habitWithCompletion.getTitle());
        assertEquals(habit.getStartDate(), habitWithCompletion.getStartDate());

        assertEquals(expectedCompletion.getCompletionDate(), actualCompletion.getCompletionDate());
    }
}
