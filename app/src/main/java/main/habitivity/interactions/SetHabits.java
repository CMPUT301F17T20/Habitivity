package main.habitivity.interactions;

import java.util.ArrayList;
import java.util.List;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.IHabitRepository;

/**
 * Created by Shally on 2017-11-25.
 */

public class SetHabits {

    private IHabitRepository habitRepository;

    public SetHabits(IHabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    /**
     * Gets a list of habits from our habit repo
     * @return a list of habits from our habit repo
     */
    public void setHabits(ArrayList<Habit> habits) {
        habitRepository.setHabits(habits);
    }

    public void setHabitEvents(ArrayList<HabitEvent> habitEvents){
        habitRepository.setHabitEvents(habitEvents);
    }
}