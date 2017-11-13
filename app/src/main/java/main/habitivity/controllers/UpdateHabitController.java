package main.habitivity.controllers;

import main.habitivity.habits.HabitEvent;
import main.habitivity.interactions.HabitInteractionsFactory;

public class UpdateHabitController {
    private HabitInteractionsFactory habitInteractionsFactory;

    public UpdateHabitController(HabitInteractionsFactory habitInteractionsFactory) {
        this.habitInteractionsFactory = habitInteractionsFactory;
    }

    public void updateHabit(AddHabitRequest addHabitRequest) {
        habitInteractionsFactory.updateHabit().update(addHabitRequest.getId(), addHabitRequest.getStartDate(), addHabitRequest.getReason(), addHabitRequest.getDaysOfTheWeek());
    }

    public void updateHabitEvent(HabitEvent habitEvent) {
        habitInteractionsFactory.updateHabitEvent().update(habitEvent.getId(), habitEvent.getCompletionDate(), habitEvent.getComment());
    }

}
