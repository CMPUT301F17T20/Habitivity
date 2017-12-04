package main.habitivity.controllers;
/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */

import android.location.Location;
import android.support.annotation.Nullable;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.interactions.HabitInteractionsFactory;


import java.util.List;
import java.util.Map;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.interactions.HabitInteractionsFactory;

public class LocationsController {
    private HabitInteractionsFactory habitInteractionsFactory;


    public LocationsController(HabitInteractionsFactory habitInteractionsFactory) {
        this.habitInteractionsFactory = habitInteractionsFactory;
    }

    /**
     * Get a list of our locations to our habit events
     * @return list of habits
     */
    public List<Location> getLocations() {
        return habitInteractionsFactory.getHabitEvents().getListOfHabitLocations();
    }


    public Map<String, Location> getMyLocations(final String filter) {
        ArrayList<HabitEvent> habitEventsToFilter = habitInteractionsFactory.getHabitEvents().getSortedEventsForMaps(filter);
        return habitInteractionsFactory.getHabitEvents().getListOfMyHabitLocations(habitEventsToFilter);
    }

    public Map<String, Location> getFriendsLocations(final String filter) {
        ArrayList<HabitEvent> habitEventsToFilter = habitInteractionsFactory.getHabitEvents().getSortedEventsForMaps(filter);
        return habitInteractionsFactory.getHabitEvents().getListOfFriendsHabitLocations(habitEventsToFilter);
    }

}
