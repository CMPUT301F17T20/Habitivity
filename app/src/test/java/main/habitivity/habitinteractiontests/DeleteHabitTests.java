package main.habitivity.habitinteractiontests;

import org.junit.Before;
import org.junit.Test;

import main.habitivity.habits.HabitRepository;
import main.habitivity.habits.IHabitRepository;
import main.habitivity.interactions.DeleteHabit;

import static org.mockito.Mockito.*;

public class DeleteHabitTests {
    private IHabitRepository habitRepository;
    private DeleteHabit deleteHabit;

    @Before
    public void set_up() {
        habitRepository = mock(IHabitRepository.class);
        deleteHabit = new DeleteHabit(habitRepository);
    }

    @Test
    public void test_delete_calls() {
        String habitIdToDelete = "habitId";
        deleteHabit.delete(habitIdToDelete);

        verify(habitRepository).removeHabit(habitIdToDelete);
    }
}
