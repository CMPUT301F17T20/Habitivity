package main.habitivity.controllers;


import java.util.List;

import main.habitivity.controllers.IController;
import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.HabitRepository;
import main.habitivity.interactions.HabitInteractionsFactory;
import main.habitivity.views.IAddHabitView;

public class HabitListController {
    private HabitInteractionsFactory habitInteractionsFactory;


    public HabitListController(HabitInteractionsFactory habitInteractionsFactory) {
        this.habitInteractionsFactory = habitInteractionsFactory;
    }

    public List<Habit> getHabits() {
        return habitInteractionsFactory.getHabits().getListOfHabits();
    }

    public List<HabitEvent> getHabitEvents() {
        return habitInteractionsFactory.getHabitEvents().getListOfHabitEvents();
    }

}