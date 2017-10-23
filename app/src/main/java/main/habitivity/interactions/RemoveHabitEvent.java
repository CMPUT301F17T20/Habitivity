package main.habitivity.interactions;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitRepository;

public class RemoveHabitEvent {
    private HabitRepository habitRepository;

    public RemoveHabitEvent(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public void remove(String habitId, String completionId) {

    }

    private void removeCompletionFromHabit(Habit habit, String completionId) {

    }
}
