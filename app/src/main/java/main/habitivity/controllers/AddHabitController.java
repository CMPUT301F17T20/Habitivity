package main.habitivity.controllers;

import main.habitivity.interactions.HabitInteractionsFactory;

/**
 * Controls adding a habit
 */
public class AddHabitController {
    private HabitInteractionsFactory habitInteractionsFactory;

    public AddHabitController(HabitInteractionsFactory habitInteractionsFactory) {
        this.habitInteractionsFactory = habitInteractionsFactory;
    }

    /**
     * Processes the request and add habits to our repository
     * @param addHabitRequest
     */
    public void addHabit(AddHabitRequest addHabitRequest) {
        habitInteractionsFactory.addHabit().add(addHabitRequest.getId(), addHabitRequest.getStartDate(), addHabitRequest.getDaysOfTheWeek());
    }
}