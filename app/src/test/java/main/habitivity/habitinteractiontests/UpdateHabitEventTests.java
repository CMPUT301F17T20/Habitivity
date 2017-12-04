package main.habitivity.habitinteractiontests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import main.habitivity.exceptions.ImageTooLargeException;
import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.IHabitRepository;
import main.habitivity.interactions.AddHabitEvent;
import main.habitivity.interactions.IClock;
import main.habitivity.interactions.UpdateHabit;
import main.habitivity.interactions.UpdateHabitEvent;
import main.habitivity.users.User;
import main.habitivity.users.UserContainer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Shally on 2017-12-04.
 */

public class UpdateHabitEventTests {
    private IClock clock;
    private IHabitRepository habitRepository;
    private UpdateHabitEvent updateHabitEvent;

    @Before
    public void set_up() {
    }

    @Test
    public void test_updateHabitEvent() {
        clock = mock(IClock.class);
        habitRepository = mock(IHabitRepository.class);
        updateHabitEvent = new UpdateHabitEvent(habitRepository);
        User user = new User("test", new ArrayList<Habit>(), new ArrayList<HabitEvent>(), new ArrayList<String>(), new ArrayList<String>());

        HabitEvent habitEvent = new HabitEvent(new Date());
        String comment = "comment";

        habitEvent.setComment(comment);
        habitEvent.setId(comment);

        ArrayList<HabitEvent> habitEvents = new ArrayList<>();
        habitEvents.add(habitEvent);
        user.setHabitEvents(habitEvents);
        UserContainer.getInstance().setUser(user);

        try {
            updateHabitEvent.update(habitEvent, null, new Date(), null, null, null);
        } catch (ImageTooLargeException e) {
            e.printStackTrace();
        }

        ArgumentCaptor<HabitEvent> habitCaptor = ArgumentCaptor.forClass(HabitEvent.class);
        verify(habitRepository).updateHabitEvent(habitCaptor.capture(), habitCaptor.capture());

    }
}
