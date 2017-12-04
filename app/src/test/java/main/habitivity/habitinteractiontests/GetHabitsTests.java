package main.habitivity.habitinteractiontests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.searchbox.core.Get;
import main.habitivity.habits.Habit;
import main.habitivity.habits.IHabitRepository;
import main.habitivity.interactions.AddHabit;
import main.habitivity.interactions.GetHabits;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Shally on 2017-12-04.
 */

public class GetHabitsTests {

    private IHabitRepository habitRepository;
    private GetHabits getHabits;

    @Before
    public void set_up() {
    }

    @Test
    public void test_getHabitsRequest() {
        habitRepository = mock(IHabitRepository.class);
        GetHabits getHabits = new GetHabits(habitRepository);

        getHabits.getListOfHabits();
        verify(habitRepository).getHabits();

    }
}
