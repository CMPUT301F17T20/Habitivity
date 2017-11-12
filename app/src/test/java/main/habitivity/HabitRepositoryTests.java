package main.habitivity;


import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitRepository;
import main.habitivity.services.AndroidFileHandler;
import main.habitivity.services.LocalHabitService;
import main.habitivity.services.WhichHabitService;

import static org.mockito.Mockito.mock;

public class HabitRepositoryTests extends ActivityInstrumentationTestCase2 {

    public HabitRepositoryTests() {
        super(main.habitivity.HabitivityMain.class);
    }

    public void testHabits() {
        Context context = mock(Context.class);
        AndroidFileHandler fileHandler = new AndroidFileHandler(context);
        HabitRepository repo = new HabitRepository(new LocalHabitService(fileHandler));
        Habit habit = new Habit();

        repo.addHabit(habit);

        assertEquals(repo.getHabit(habit.getId()), habit);
        assertEquals(repo.getHabits().get(0), habit);

        repo.updateHabit(repo.getHabit(habit.getId()));
        assertEquals(repo.getHabit(habit.getId()), habit);


        repo.removeHabit(habit.getId());

        assertTrue(repo.getHabits().isEmpty());
    }
}
