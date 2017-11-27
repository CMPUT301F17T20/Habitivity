package main.habitivity.interactions;

import java.util.ArrayList;
import java.util.List;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.IHabitRepository;

/**
 * Interaction class to set habits and habitevent in our habit repository
 */
public class SetHabits {

    private IHabitRepository habitRepository;

    public SetHabits(IHabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    /**
     * Sets a list of habits to our habit repo
     * @param habits - a list of habits to set
     */
    public void setHabits(ArrayList<Habit> habits) {
        habitRepository.setHabits(habits);
    }

    /**
     * Sets a list of habitEvents to our habit repo
     * @param habitEvents - a list of habitEvents to set
     */
    public void setHabitEvents(ArrayList<HabitEvent> habitEvents){
        habitRepository.setHabitEvents(habitEvents);
    }
}