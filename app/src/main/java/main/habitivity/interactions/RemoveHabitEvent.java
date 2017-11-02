package main.habitivity.interactions;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.HabitRepository;
import main.habitivity.habits.IHabitRepository;

public class RemoveHabitEvent {
    private IHabitRepository habitRepository;

    public RemoveHabitEvent(IHabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public void remove(String habitId, String completionId) {
        Habit habit = habitRepository.getHabit(habitId);

        if (habit != null) {
            removeEventFromHabit(habit, completionId);
        }
    }

    private void removeEventFromHabit(Habit habit, String completionId) {
        HabitEvent habitCompletion = habit.getHabitEvent(completionId);

        if (habitCompletion != null) {
            habit.getCompletedEvents().remove(habitCompletion);
            habitRepository.updateHabit(habit);
        }

    }
}
