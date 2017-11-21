/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */

package main.habitivity.interactions;

import android.location.Location;

import java.util.Date;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.HabitRepository;
import main.habitivity.habits.IHabitRepository;

/**
 * Interaction class that helps add habitEvents to our repo
 */
public class AddHabitEvent {
    private IClock clock;
    private IHabitRepository habitRepository;

    public AddHabitEvent(IHabitRepository habitRepository, IClock clock) {
        this.habitRepository = habitRepository;
        this.clock = clock;
    }

    /**
     * complete the habit. I.e it becomes a habit event
     * @param id of habit
     */
    public void complete(String id) {
        Habit habit = habitRepository.getHabit(id);

        if (habit != null) {
            habitRepository.updateHabit(this.getHabitUpdatedWithEvent(habit));
        }
    }

    /**
     * Add a habit event
     * @param id  of habit event
     * @param comment of habit event
     * @param location of habit event
     */
    public void add(String id, String comment, Location location){
        HabitEvent habitEvent = generateHabitEvent();
        habitEvent.setComment(comment);
        habitEvent.setLocation(location);
        habitEvent.setId(id);

        habitRepository.addHabitEvent(habitEvent);
    }

    /**
     * Get the habit with the updated event
     * @param habit
     * @return updated habit
     */
    private Habit getHabitUpdatedWithEvent(Habit habit) {
        habit.getCompletedEvents().add(generateHabitEvent());
        return habit;
    }

    /**
     * Generate a new habitEvent
     * @return a new habitEvent
     */
    private HabitEvent generateHabitEvent() {

        return new HabitEvent(clock.getCurrentDate());
    }
}
