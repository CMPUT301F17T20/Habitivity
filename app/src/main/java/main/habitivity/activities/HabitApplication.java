/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */
package main.habitivity.activities;

import android.app.Application;

import main.habitivity.controllers.AddHabitController;
import main.habitivity.controllers.AddHabitEventController;
import main.habitivity.controllers.AllUsersController;
import main.habitivity.controllers.HabitListController;
import main.habitivity.controllers.LocationsController;
import main.habitivity.controllers.UpdateHabitController;
import main.habitivity.habits.HabitRepository;
import main.habitivity.interactions.Clock;
import main.habitivity.interactions.HabitInteractionsFactory;
import main.habitivity.services.AndroidFileHandler;
import main.habitivity.services.ConnectivityService;
import main.habitivity.services.LocalHabitService;
import main.habitivity.services.WhichHabitService;


/**
 * This class exposes dependencies to the rest of the system. It takes
 * care of the lifetime for each dependency, so all the consumers
 * need to care about is what dependency they need.
 */
public class HabitApplication extends Application {
    private HabitRepository habitRepository;
    private HabitInteractionsFactory habitInteractionsFactory;

    public HabitRepository getHabitRepository() {
        ensureHabitRepository();
        return habitRepository;
    }

    private void ensureHabitRepository() {
        if (habitRepository == null) {
            AndroidFileHandler fileHandler = new AndroidFileHandler(this);
            WhichHabitService habitService = new WhichHabitService(new ConnectivityService(getApplicationContext()), fileHandler);
            habitRepository = new HabitRepository(habitService);
        }
    }

    public HabitInteractionsFactory getHabitInteractionsFactory() {
        ensureHabitInteractionsFactory();
        return habitInteractionsFactory;
    }

    private void ensureHabitInteractionsFactory() {
        if (habitInteractionsFactory == null) {
            habitInteractionsFactory = new HabitInteractionsFactory(getHabitRepository(), new Clock());
        }
    }

    public HabitListController getHabitListController() {
        return new HabitListController(getHabitInteractionsFactory());
    }

    public UpdateHabitController getUpdateHabitController(){
        return new UpdateHabitController(getHabitInteractionsFactory());
    }

    public AddHabitEventController getAddHabitEventsController(){
        return new AddHabitEventController(getHabitInteractionsFactory());
    }

    public LocationsController getLocationsController(){
        return new LocationsController(getHabitInteractionsFactory());
    }

    public AllUsersController getAllUserController(){
        return new AllUsersController();
    }
//
//    public TodaysHabitsController getTodaysHabitsController() {
//        return new TodaysHabitsController(getHabitRepository(), getHabitInteractionsFactory());
//    }


    public AddHabitController getAddHabitController() {
        return new AddHabitController(getHabitInteractionsFactory());
    }
}