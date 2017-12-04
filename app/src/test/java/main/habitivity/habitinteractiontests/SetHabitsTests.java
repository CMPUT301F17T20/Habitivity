package main.habitivity.habitinteractiontests;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import main.habitivity.habits.Habit;
import main.habitivity.habits.IHabitRepository;
import main.habitivity.interactions.SetHabits;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Shally on 2017-12-04.
 */

public class SetHabitsTests {
    private IHabitRepository habitRepository;
    private SetHabits setHabits;

    @Before
    public void set_up() {
    }

    @Test
    public void test_getHabitsRequest() {
        habitRepository = mock(IHabitRepository.class);
        SetHabits setHabits = new SetHabits(habitRepository);

        ArrayList<Habit> habits = new ArrayList<>();
        setHabits.setHabits(habits);
        verify(habitRepository).setHabits(habits);

    }
}
