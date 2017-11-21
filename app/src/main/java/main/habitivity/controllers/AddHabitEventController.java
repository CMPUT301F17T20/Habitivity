/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */

package main.habitivity.controllers;

import android.location.Location;

import main.habitivity.habits.HabitEvent;
import main.habitivity.interactions.HabitInteractionsFactory;


/**
 * Controls add habitEvents
 */
public class AddHabitEventController {

    private HabitInteractionsFactory habitInteractionsFactory;

    public AddHabitEventController(HabitInteractionsFactory habitInteractionsFactory) {
        this.habitInteractionsFactory = habitInteractionsFactory;
    }

    /**
     * Adds the habit event to our habit repository
     * @param title - title of habitevent
     * @param comment - comment of habitevent
     * @param location - location of habitevent
     */
    public void addHabitEvent(String title, String comment, Location location) {
        habitInteractionsFactory.addHabitEvent().add(title, comment, location);
    }

}
