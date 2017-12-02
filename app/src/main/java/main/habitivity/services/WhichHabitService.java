/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */
package main.habitivity.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.habitivity.controllers.ElasticsearchController;
import main.habitivity.exceptions.ElasticSearchConnectivityException;
import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.users.User;
import main.habitivity.users.UserContainer;

/**
 * TODO. WILL BE IMPLEMENTED WHEN WE DO ELASTIC SEARCH
 */
public class WhichHabitService {
    private AndroidFileHandler fileHandler;
    private IConnectivityService connectivityService;

    public WhichHabitService(IConnectivityService connectivityService, AndroidFileHandler fileHandler) {
        this.connectivityService = connectivityService;
        this.fileHandler = fileHandler;
    }

    public void addHabitRequest(Habit habit){
        User currentlylogged = UserContainer.getInstance().getUser();
        currentlylogged.addHabit(habit);
        if(this.connectivityService.isInternetAvailable()) {
            ElasticsearchController.UpdateUserTask updateUserTask = new ElasticsearchController.UpdateUserTask();
            User user = UserContainer.getInstance().getUser();
            updateUserTask.execute(UserContainer.getInstance().getUser());
            //sync with local copy
            this.saveUser(user);
        }
        else {
            this.addHabit(habit);
        }


    }

    public void deleteHabitRequest(Habit habit){
        User currentlylogged = UserContainer.getInstance().getUser();
        currentlylogged.removeHabit(habit);
        if(this.connectivityService.isInternetAvailable()) {
            ElasticsearchController.UpdateUserTask updateUserTask = new ElasticsearchController.UpdateUserTask();
            User user = UserContainer.getInstance().getUser();
            updateUserTask.execute(UserContainer.getInstance().getUser());
            //sync
            this.saveUser(user);
        }
        else{
            this.deleteHabit(habit);
        }

    }

    public void updateHabitRequest(Habit oldHabit, Habit newHabit){
        User currentlylogged = UserContainer.getInstance().getUser();
        currentlylogged.updateHabit(oldHabit, newHabit);
        if(this.connectivityService.isInternetAvailable()) {
            ElasticsearchController.UpdateUserTask updateUserTask = new ElasticsearchController.UpdateUserTask();
            User user = UserContainer.getInstance().getUser();
            updateUserTask.execute(UserContainer.getInstance().getUser());
        }
        else{
            this.updateHabit(oldHabit, newHabit);
        }

    }

    public void addHabitEventRequest(HabitEvent habitEvent){
        User currentlylogged = UserContainer.getInstance().getUser();
        currentlylogged.addHabitEvent(habitEvent);
        if(this.connectivityService.isInternetAvailable()) {
            ElasticsearchController.UpdateUserTask updateUserTask = new ElasticsearchController.UpdateUserTask();
            User user = UserContainer.getInstance().getUser();
            updateUserTask.execute(UserContainer.getInstance().getUser());
            //sync with local copy
            this.saveUser(user);
        }
        else {
            this.addHabitEvent(habitEvent);
        }


    }

    public void deleteHabitEventRequest(HabitEvent habitEvent){
        User currentlylogged = UserContainer.getInstance().getUser();
        currentlylogged.removeHabitEvent(habitEvent);
        if(this.connectivityService.isInternetAvailable()) {
            ElasticsearchController.UpdateUserTask updateUserTask = new ElasticsearchController.UpdateUserTask();
            User user = UserContainer.getInstance().getUser();
            updateUserTask.execute(UserContainer.getInstance().getUser());
            //sync
            this.saveUser(user);
        }
        else{
            this.deleteHabitEvent(habitEvent);
        }

    }

    public void updateHabitEventRequest(HabitEvent oldHabitEvent, HabitEvent newHabitEvent){
        User currentlylogged = UserContainer.getInstance().getUser();
        currentlylogged.updateHabitEvent(oldHabitEvent, newHabitEvent);
        if(this.connectivityService.isInternetAvailable()) {
            ElasticsearchController.UpdateUserTask updateUserTask = new ElasticsearchController.UpdateUserTask();
            User user = UserContainer.getInstance().getUser();
            updateUserTask.execute(UserContainer.getInstance().getUser());
        }
        else{
            this.updateHabitEvent(oldHabitEvent, newHabitEvent);
        }

    }

    public List<HabitEvent> getHabitEvents() {
        User user = this.loadUser();
        ArrayList<HabitEvent> habitEvents = user.getHabitEvents();
        return habitEvents;
    }

    public void addHabitEvent(HabitEvent habitEvent) {
        User user = this.loadUser();
        ArrayList<HabitEvent> habitEvents = user.getHabitEvents();

        if (!habitEvents.contains(habitEvent)) {
            habitEvents.add(habitEvent);
            user.setHabitEvents(habitEvents);
            saveUser(user);
        }
    }

    public void deleteHabitEvent(HabitEvent habitEvent) {
        User user = this.loadUser();
        ArrayList<HabitEvent> habitEvents = user.getHabitEvents();

        if (!habitEvents.contains(habitEvent)) {
            habitEvents.remove(habitEvent);
            user.setHabitEvents(habitEvents);
            saveUser(user);
        }
    }

    public void updateHabitEvent(HabitEvent oldHabitEvent, HabitEvent habitEvent) {
        User user = this.loadUser();
        ArrayList<HabitEvent> habitEvents = user.getHabitEvents();

        if (habitEvents.contains(oldHabitEvent)) {
            int index = habitEvents.indexOf(oldHabitEvent);
            if(index!= -1){
                habitEvents.set(index, habitEvent);
            }
            user.setHabitEvents(habitEvents);
            saveUser(user);
        }
    }

    public List<Habit> getHabits() {
        User user = this.loadUser();
        ArrayList<Habit> habits = user.getHabits();
        return habits;
    }

    public void addHabit(Habit habit) {
        User user = this.loadUser();
        ArrayList<Habit> habits = user.getHabits();

        if (!habits.contains(habit)) {
            habits.add(habit);
            user.setHabits(habits);
            saveUser(user);
        }
    }

    public void deleteHabit(Habit habit) {
        User user = this.loadUser();
        ArrayList<Habit> habits = user.getHabits();

        if (habits.contains(habit)) {
            habits.remove(habit);
            user.setHabits(habits);
            saveUser(user);
        }

    }

    public void updateHabit(Habit oldHabit, Habit habit) {
        User user = this.loadUser();
        ArrayList<Habit> habits = user.getHabits();

        if (habits.contains(oldHabit)) {
            int index = habits.indexOf(oldHabit);
            if(index!= -1){
                habits.set(index, habit);
            }
            user.setHabits(habits);
            saveUser(user);
        }
    }

    private void saveUser(User user) {
        String serializedUser = getGson().toJson(user);
        fileHandler.saveStringToFile("testSaveFileForUser.txt", serializedUser);
    }


    private User loadUser() {
        String serializedUser= fileHandler.loadFileAsString("testSaveFileForUser.txt");

        if (serializedUser.isEmpty() || serializedUser == null) {
            return new User(UserContainer.getInstance().getUser().getUserName(), new ArrayList<Habit>(), new ArrayList<HabitEvent>(), new ArrayList<String>(), new ArrayList<String>());
        } else {
            return getGson().fromJson(serializedUser, new TypeToken<User>() {}.getType());
        }
    }
    /**
     * Gets gson version of a string
     * @return
     */
    private Gson getGson() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();
    }

}
