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
