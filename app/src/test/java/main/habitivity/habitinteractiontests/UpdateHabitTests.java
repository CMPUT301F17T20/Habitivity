package main.habitivity.habitinteractiontests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.IHabitRepository;
import main.habitivity.interactions.AddHabit;
import main.habitivity.interactions.UpdateHabit;
import main.habitivity.users.User;
import main.habitivity.users.UserContainer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Shally on 2017-12-04.
 */

public class UpdateHabitTests {
    private IHabitRepository habitRepository;
    private UpdateHabit updateHabit;

    @Before
    public void set_up() {
    }

    @Test
    public void test_updateHabitRequest() {
        habitRepository = mock(IHabitRepository.class);
        User user = new User("test", new ArrayList<Habit>(), new ArrayList<HabitEvent>(), new ArrayList<String>(), new ArrayList<String>());

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

        ArrayList<Habit> habits = new ArrayList<>();
        habits.add(habit);
        Habit oldHabit = habits.get(0);
        user.setHabits(habits);
        UserContainer.getInstance().setUser(user);

        habit.setTitle("hi");
        updateHabit = new UpdateHabit(habitRepository);
        updateHabit.update(habit.getId(), oldHabit, new Date(), null, null, null);

        ArgumentCaptor<Habit> habitCaptor = ArgumentCaptor.forClass(Habit.class);
        verify(habitRepository).updateHabit(habitCaptor.capture(), habitCaptor.capture());

    }
}
