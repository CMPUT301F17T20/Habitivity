package main.habitivity.interactions;

import main.habitivity.habits.HabitRepository;
import main.habitivity.habits.IHabitRepository;

public class DeleteHabit {
    private IHabitRepository habitRepository;

    public DeleteHabit(IHabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public void delete(String habitId) {
        habitRepository.removeHabit(habitId);
    }
}
