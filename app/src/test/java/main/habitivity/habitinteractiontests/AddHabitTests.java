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
    }

    @Test
    public void test_addHabitRequest() {
        habitRepository = mock(IHabitRepository.class);
        Habit habit = new Habit();
        String id = "new habit";
        Date startDate = new Date();
        List<Integer> days = new ArrayList<>();
        days.add(1);
        String habitType = "red";
        String userName = "name";
        habit.setHabitType(habitType);
        habit.setId(id);
        habit.setTitle(id);
        habit.setStartDate(startDate);
        habit.setDaysOfTheWeekToComplete(days);
        habit.setUserName(userName);

        addHabit = new AddHabit(habitRepository);
        addHabit.add(habit.getId(), habit.getStartDate(), habit.getDaysOfTheWeekToComplete(), habit.getHabitType(), habit.getUserName());

        ArgumentCaptor<Habit> habitCaptor = ArgumentCaptor.forClass(Habit.class);
        verify(habitRepository).addHabit(habitCaptor.capture());

    }

}
