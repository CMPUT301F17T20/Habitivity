package main.habitivity.interactions;

import main.habitivity.habits.HabitRepository;

public class DeleteHabit {
    private HabitRepository habitRepository;

    public DeleteHabit(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public void delete(String habitId) {
    }
}
