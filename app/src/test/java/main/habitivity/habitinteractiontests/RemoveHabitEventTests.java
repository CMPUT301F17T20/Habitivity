package main.habitivity.habitinteractiontests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.HabitRepository;
import main.habitivity.habits.IHabitRepository;
import main.habitivity.interactions.DeleteHabit;
import main.habitivity.interactions.RemoveHabitEvent;
import main.habitivity.users.User;
import main.habitivity.users.UserContainer;

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
    public void test_removeHabitEvent() {
        habitRepository = mock(IHabitRepository.class);
        removeCompletion = new RemoveHabitEvent(habitRepository);
        User user = new User("test", new ArrayList<Habit>(), new ArrayList<HabitEvent>(), new ArrayList<String>(), new ArrayList<String>());


        HabitEvent habitEvent = new HabitEvent(new Date());
        String comment = "comment";

        habitEvent.setComment(comment);
        habitEvent.setId(comment);

        ArrayList<HabitEvent> habitEvents = new ArrayList<>();
        habitEvents.add(habitEvent);
        user.setHabitEvents(habitEvents);
        UserContainer.getInstance().setUser(user);
        removeCompletion.remove(habitEvent);
        ArgumentCaptor<HabitEvent> habitCaptor = ArgumentCaptor.forClass(HabitEvent.class);
        verify(habitRepository).removeHabitEvent(habitCaptor.capture());
    }



}
