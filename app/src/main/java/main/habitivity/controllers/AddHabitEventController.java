package main.habitivity.controllers;

import main.habitivity.habits.HabitEvent;
import main.habitivity.interactions.HabitInteractionsFactory;


public class AddHabitEventController {

    private HabitInteractionsFactory habitInteractionsFactory;

    public AddHabitEventController(HabitInteractionsFactory habitInteractionsFactory) {
        this.habitInteractionsFactory = habitInteractionsFactory;
    }

    public void addHabitEvent(String title, String comment) {
        habitInteractionsFactory.addHabitEvent().add(title, comment);
        //dispatchOnHabitAdded();
    }

}
