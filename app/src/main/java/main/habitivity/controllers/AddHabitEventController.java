/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */

package main.habitivity.controllers;

import android.graphics.Bitmap;
import android.location.Location;

import java.util.ArrayList;
import java.util.Date;

import main.habitivity.habits.Habit;
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
     * @param image - the image of the habit event (optional) null otherwise
     */
    public void addHabitEvent(String title, String comment, Location location, Date compDate, Bitmap image) {
        habitInteractionsFactory.addHabitEvent().add(title, comment, location, compDate, image);
    }

    /**
     * Sets the list of habitEvents to our habitRepository
     * @param habitEvents - the list of habitEvents to set in our habitRepository
     */
    public void setHabitEvents(ArrayList<HabitEvent> habitEvents){
        habitInteractionsFactory.setHabits().setHabitEvents(habitEvents);
    }

}
