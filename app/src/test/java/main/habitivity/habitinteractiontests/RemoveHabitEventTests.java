package main.habitivity.habitinteractiontests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.HabitRepository;
import main.habitivity.habits.IHabitRepository;
import main.habitivity.interactions.RemoveHabitEvent;

import static org.mockito.Mockito.*;

public class RemoveHabitEventTests {
    private IHabitRepository habitRepository;
    private RemoveHabitEvent removeCompletion;

    private Habit habitWithoutCompletion;
    private Habit habitWithCompletion;

    @Before
    public void set_up() {
        habitWithoutCompletion = new Habit();
        habitWithoutCompletion.setId("Habit-ID");
        habitWithoutCompletion.setTitle("AwesomeHabit");
        habitWithoutCompletion.setStartDate(new Date(1));
        habitWithoutCompletion.setDaysOfTheWeekToComplete(Arrays.asList(Calendar.SUNDAY));

        HabitEvent habitCompletion = new HabitEvent(new Date());
        habitCompletion.setId("Completion-ID");

        habitWithCompletion = new Habit();
        habitWithCompletion.setId("Habit-ID");
        habitWithCompletion.setTitle("AwesomeHabit");
        habitWithCompletion.setStartDate(new Date());
        habitWithCompletion.setDaysOfTheWeekToComplete(Arrays.asList(Calendar.SUNDAY));
        habitWithCompletion.setCompletedEvents(Arrays.asList(habitCompletion));

        habitRepository = mock(IHabitRepository.class);
        removeCompletion = new RemoveHabitEvent(habitRepository);
    }

    @Test
    public void test_remove_ifHabitDoesNotExist_thenNoRepositoryUpdatesOccur() {
        when(habitRepository.getHabit("Habit-ID")).thenReturn(null);
        removeCompletion.remove("Habit-ID");

        verify(habitRepository, never()).updateHabit(Mockito.any(Habit.class));
    }

    @Test
    public void test_remove_ifHabitExistsButCompletionDoesNot_thenNoRepositoryUpdatesOccur() {
        when(habitRepository.getHabit("Habit-ID")).thenReturn(habitWithoutCompletion);
        removeCompletion.remove("Habit-ID");

        verify(habitRepository, never()).updateHabit(Mockito.any(Habit.class));
    }

    @Test
    public void test_remove_ifHabitAndCompletionExists_thenHabitIsUpdated() {
//        when(habitRepository.getHabit("Habit-ID")).thenReturn(habitWithCompletion);
//        removeCompletion.remove("Habit-ID", "Completion-ID");
//
//        verify(habitRepository).updateHabit(habitWithoutCompletion);
    }

}
