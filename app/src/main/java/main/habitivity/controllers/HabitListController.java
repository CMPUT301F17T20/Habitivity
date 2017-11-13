package main.habitivity.controllers;


import java.util.List;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.interactions.HabitInteractionsFactory;

public class HabitListController {
    private HabitInteractionsFactory habitInteractionsFactory;


    public HabitListController(HabitInteractionsFactory habitInteractionsFactory) {
        this.habitInteractionsFactory = habitInteractionsFactory;
    }

    public List<Habit> getHabits() {
        return habitInteractionsFactory.getHabits().getListOfHabits();
    }

    public void removeHabit(Habit habit){
        habitInteractionsFactory.deleteHabit().delete(habit.getId());
    }

    public void removeHabitEvent(HabitEvent habitEvent){
        habitInteractionsFactory.removeHabitEvent().remove(habitEvent.getId());
    }

    public List<HabitEvent> getHabitEvents() {
        return habitInteractionsFactory.getHabitEvents().getListOfHabitEvents();
    }

}