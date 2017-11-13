/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */

package main.habitivity.controllers;

import main.habitivity.habits.HabitEvent;
import main.habitivity.interactions.HabitInteractionsFactory;

/**
 * Controls updating habitEvents and habits
 */
public class UpdateHabitController {
    private HabitInteractionsFactory habitInteractionsFactory;

    public UpdateHabitController(HabitInteractionsFactory habitInteractionsFactory) {
        this.habitInteractionsFactory = habitInteractionsFactory;
    }

    /**
     * Updates a habit
     * @param addHabitRequest - habit to update
     */
    public void updateHabit(AddHabitRequest addHabitRequest) {
        habitInteractionsFactory.updateHabit().update(addHabitRequest.getId(), addHabitRequest.getStartDate(), addHabitRequest.getReason(), addHabitRequest.getDaysOfTheWeek());
    }

    /**
     * Updates a habit event
     * @param habitEvent to update
     */
    public void updateHabitEvent(HabitEvent habitEvent) {
        habitInteractionsFactory.updateHabitEvent().update(habitEvent.getId(), habitEvent.getCompletionDate(), habitEvent.getComment());
    }

}
