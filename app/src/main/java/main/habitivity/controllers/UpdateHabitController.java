package main.habitivity.controllers;

import main.habitivity.interactions.HabitInteractionsFactory;
import main.habitivity.views.IAddHabitView;

public class UpdateHabitController {
    private HabitInteractionsFactory habitInteractionsFactory;

    public UpdateHabitController(HabitInteractionsFactory habitInteractionsFactory) {
        this.habitInteractionsFactory = habitInteractionsFactory;
    }

    public void updateHabit(AddHabitRequest addHabitRequest) {
        habitInteractionsFactory.updateHabit().update(addHabitRequest.getId(), addHabitRequest.getStartDate(), addHabitRequest.getReason(), addHabitRequest.getDaysOfTheWeek());
        //dispatchOnHabitAdded();
    }

}
