/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */

package main.habitivity.interactions;

import main.habitivity.controllers.ElasticsearchController;
import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitRepository;
import main.habitivity.habits.IHabitRepository;
import main.habitivity.users.User;
import main.habitivity.users.UserContainer;

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
     * @param - of habit to delete
     */
    public void delete(Habit habit) {
        User currentlylogged = UserContainer.getInstance().getUser();
        currentlylogged.removeHabit(habit);
        habitRepository.removeHabit(habit);
    }
}
