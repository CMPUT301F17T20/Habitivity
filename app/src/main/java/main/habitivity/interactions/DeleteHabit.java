/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */

package main.habitivity.interactions;

import main.habitivity.habits.HabitRepository;
import main.habitivity.habits.IHabitRepository;

/**
 * Interaction class to help delete habits
 */
public class DeleteHabit {
    private IHabitRepository habitRepository;

    public DeleteHabit(IHabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    /**
     * Delete a habit from the habit repo
     * @param habitId of habit to delete
     */
    public void delete(String habitId) {
        habitRepository.removeHabit(habitId);
    }
}
