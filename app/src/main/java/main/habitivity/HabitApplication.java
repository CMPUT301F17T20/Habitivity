/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */
package main.habitivity;

import android.app.Application;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;

import io.searchbox.client.JestClient;
import main.habitivity.Users.User;
import main.habitivity.Users.UserController;
import main.habitivity.Users.UserService;
import main.habitivity.controllers.AddHabitController;
import main.habitivity.controllers.AddHabitEventController;
import main.habitivity.controllers.HabitListController;
import main.habitivity.controllers.LocationsController;
import main.habitivity.controllers.UpdateHabitController;
import main.habitivity.habits.HabitRepository;
import main.habitivity.interactions.Clock;
import main.habitivity.interactions.HabitInteractionsFactory;
import main.habitivity.services.AndroidFileHandler;
import main.habitivity.services.ElasticSearchService;
import main.habitivity.services.LocalHabitService;


/**
 * This class exposes dependencies to the rest of the system. It takes
 * care of the lifetime for each dependency, so all the consumers
 * need to care about is what dependency they need.
 */
public class HabitApplication extends Application {
    private HabitRepository habitRepository;
    private JestClient jestClient;
    private HabitInteractionsFactory habitInteractionsFactory;
    private String userId;

    public HabitRepository getHabitRepository() {
        ensureHabitRepository();
        return habitRepository;
    }

    public void setHabitRepository(HabitRepository repo){
        this.habitRepository = repo;
    }

    public void setHabitRepoUserId(String id){
        HabitRepository repo = getHabitRepository();
        this.userId = id;
        repo.setID(id);
    }

    private void ensureHabitRepository() {
        if (habitRepository == null) {
            AndroidFileHandler fileHandler = new AndroidFileHandler(this);
            LocalHabitService habitService = new LocalHabitService(fileHandler);
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
        this.habitInteractionsFactory.setHabitRepository(getHabitRepository());
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

    public UserController getUserController(){
        return new UserController(getUserService());
    }

    private UserService getUserService() {
        ElasticSearchService service = getUserElasticSearchService();
        return new UserService(service);
    }

    private ElasticSearchService<User> getUserElasticSearchService() {
        return new ElasticSearchService.Builder<User>()
                .setIndex("CMPUT301F17T20")
                .setType("user")
                .setTypeClass(User.class)
                .setJestClient(getJestClient())
                .build();
    }

    private JestClient getJestClient() {
        if (jestClient == null) {
            DroidClientConfig config = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080")
                    .build();


            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            jestClient = factory.getObject();
        }
        return jestClient;
    }
//
//    public TodaysHabitsController getTodaysHabitsController() {
//        return new TodaysHabitsController(getHabitRepository(), getHabitInteractionsFactory());
//    }


    public AddHabitController getAddHabitController() {
        return new AddHabitController(getHabitInteractionsFactory());
    }
}