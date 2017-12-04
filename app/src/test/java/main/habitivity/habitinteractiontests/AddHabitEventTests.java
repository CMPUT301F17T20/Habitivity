package main.habitivity.habitinteractiontests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.HabitRepository;
import main.habitivity.habits.IHabitRepository;
import main.habitivity.interactions.AddHabit;
import main.habitivity.interactions.AddHabitEvent;
import main.habitivity.interactions.IClock;

import static org.hamcrest.core.IsInstanceOf.any;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AddHabitEventTests {
    private IClock clock;
    private IHabitRepository habitRepository;
    private AddHabitEvent completeHabit;
    private AddHabitEvent addHabitEvent;

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
        habitRepository = mock(IHabitRepository.class);
        completeHabit = new AddHabitEvent(habitRepository, clock);
    }

    @Test
    public void test_addHabitEvent() {
        clock = mock(IClock.class);
        habitRepository = mock(IHabitRepository.class);
        addHabitEvent = new AddHabitEvent(habitRepository, clock);

        HabitEvent habitEvent = new HabitEvent(new Date());
        String comment = "comment";

        habitEvent.setComment(comment);
        habitEvent.setId(comment);

        addHabitEvent.add(habitEvent.getId(), habitEvent.getComment(), null, null, null, Boolean.FALSE);
        ArgumentCaptor<HabitEvent> habitCaptor = ArgumentCaptor.forClass(HabitEvent.class);
        verify(habitRepository).addHabitEvent(habitCaptor.capture());

    }
}
