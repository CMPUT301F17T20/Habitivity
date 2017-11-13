/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */
package main.habitivity.controllers;

import main.habitivity.interactions.HabitInteractionsFactory;

/**
 * Controls adding a habit
 */
public class AddHabitController {
    private HabitInteractionsFactory habitInteractionsFactory;

    public AddHabitController(HabitInteractionsFactory habitInteractionsFactory) {
        this.habitInteractionsFactory = habitInteractionsFactory;
    }

    /**
     * Processes the request and add habits to our repository
     * @param addHabitRequest
     */
    public void addHabit(AddHabitRequest addHabitRequest) {
        habitInteractionsFactory.addHabit().add(addHabitRequest.getId(), addHabitRequest.getStartDate(), addHabitRequest.getDaysOfTheWeek());
    }
}