package main.habitivity.controllers;


import java.util.List;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.interactions.HabitInteractionsFactory;

/**
 * Controls getting the habits and habitevents stored in our repo. Also controls removing
 * habitEvents and habits. Will move the remove functions to a remove controller later.
 */
public class HabitListController {
    private HabitInteractionsFactory habitInteractionsFactory;


    public HabitListController(HabitInteractionsFactory habitInteractionsFactory) {
        this.habitInteractionsFactory = habitInteractionsFactory;
    }

    /**
     * Get a list of habits stored in our repo
     * @return list of habits
     */
    public List<Habit> getHabits() {
        return habitInteractionsFactory.getHabits().getListOfHabits();
    }

    /**
     * Remove the habit from our habit repo
     * @param habit to remove
     */
    public void removeHabit(Habit habit){
        habitInteractionsFactory.deleteHabit().delete(habit.getId());
    }

    /**
     * Remove the habitEvent from our habit repo
     * @param habitEvent to remove
     */
    public void removeHabitEvent(HabitEvent habitEvent){
        habitInteractionsFactory.removeHabitEvent().remove(habitEvent.getId());
    }

    /**
     * Gets the list of HabitEvents in our repo
     * @return a list of habit events
     */
    public List<HabitEvent> getHabitEvents() {
        return habitInteractionsFactory.getHabitEvents().getListOfHabitEvents();
    }

}