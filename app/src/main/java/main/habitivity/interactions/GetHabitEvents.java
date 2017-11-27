/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */

package main.habitivity.interactions;

import android.location.Location;

import java.util.List;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.IHabitRepository;

/**
 * Interaction class to get a list of habitEvents from our repo
 */
public class GetHabitEvents {
    private IHabitRepository habitRepository;

    public GetHabitEvents(IHabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    /**
     * Gets a list of habitEvents from our repo
     * @return a list of HabitEvents
     */
    public List<HabitEvent> getListOfHabitEvents() {
        return habitRepository.getHabitEvents();
    }

    /**
     * Gets a list of habit event locations
     * @return a list of habit event locations
     */
    public List<Location> getListOfHabitLocations(){
        return habitRepository.getHabitLocations();
    }
}
