package main.habitivity.interactions;

import java.util.Date;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.HabitRepository;

public class AddHabitEvent {
    private IClock clock;
    private HabitRepository habitRepository;

    public AddHabitEvent(HabitRepository habitRepository, IClock clock) {

    }

    public void complete(String id) {

    }

    private Habit getHabitUpdatedWithCompletion(Habit habit) {
        return habit;
    }

    private HabitEvent generateCompletion() {
        return new HabitEvent(new Date());
    }
}
