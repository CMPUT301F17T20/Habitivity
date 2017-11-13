package main.habitivity.controllers;

import main.habitivity.habits.HabitEvent;
import main.habitivity.interactions.HabitInteractionsFactory;


/**
 * Controls add habitEvents
 */
public class AddHabitEventController {

    private HabitInteractionsFactory habitInteractionsFactory;

    public AddHabitEventController(HabitInteractionsFactory habitInteractionsFactory) {
        this.habitInteractionsFactory = habitInteractionsFactory;
    }

    /**
     * Adds the habit event to our habit repository
     * @param title - title of habitevent
     * @param comment - comment of habitevent
     */
    public void addHabitEvent(String title, String comment) {
        habitInteractionsFactory.addHabitEvent().add(title, comment);
    }

}
