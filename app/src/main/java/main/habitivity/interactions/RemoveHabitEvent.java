package main.habitivity.interactions;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitRepository;
import main.habitivity.habits.IHabitRepository;

public class RemoveHabitEvent {
    private IHabitRepository habitRepository;

    public RemoveHabitEvent(IHabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public void remove(String habitId, String completionId) {

    }

    private void removeCompletionFromHabit(Habit habit, String completionId) {

    }
}
