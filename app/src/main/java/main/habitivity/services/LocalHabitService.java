/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */
package main.habitivity.services;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;

/**
 * Created by Shally on 2017-11-09.
 *
 * Provides CRUD (create, read, update, delete) methods for habits and habit events stored locally
 * on the device
 */
public class LocalHabitService implements IHabitService {
    private AndroidFileHandler fileHandler;

    public LocalHabitService(AndroidFileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @Override
    /**
     * Gets a list of habit events
     */
    public List<HabitEvent> getHabitEvents(){

        return new ArrayList<>(loadHabitEvents().values());
    }

    @Override
    /**
     * Saves habit events to a file
     */
    public void addHabitEvent(HabitEvent habitEvent){
        Map<String, HabitEvent> habitEvents = loadHabitEvents();

        if (!habitEvents.containsKey(habitEvent.getId())) {
            habitEvents.put(habitEvent.getId(), habitEvent);
            saveHabitEvents(habitEvents);
        }

    }

    /**
     * Deletes the habit event and removes it from our locally stored file
     * @param id of habit event to remove
     */
    @Override
    public void deleteHabitEvent(String id){
        Map<String, HabitEvent> habitEvents = loadHabitEvents();

        if (habitEvents.containsKey(id)) {
            habitEvents.remove(id);
            saveHabitEvents(habitEvents);
        }
    }

    /**
     * Update the habitEvent in our locally stored file
     * @param habitEvent to update
     */
    @Override
    public void updateHabitEvent(HabitEvent habitEvent){
        Map<String, HabitEvent> habitEvents = loadHabitEvents();

        if (habitEvents.containsKey(habitEvent.getId())) {
            habitEvents.put(habitEvent.getId(), habitEvent);
            saveHabitEvents(habitEvents);
        }
    }


    /**
     * Gets a list of habits from our locally stored file
     * @return a list of habits
     */
    @Override
    public List<Habit> getHabits() {

        return new ArrayList<>(loadHabits().values());
    }

    /**
     * Saves habit events to a file
     * @param habit to save locally
     */
    @Override
    public void addHabit(Habit habit) {
        Map<String, Habit> habits = loadHabits();

        if (!habits.containsKey(habit.getId())) {
            habits.put(habit.getId(), habit);
            saveHabits(habits);
        }
    }

    /**
     * Delete a habit from our locally stored file and from the habit repo
     * @param id of habit to delete
     */
    @Override
    public void deleteHabit(String id) {
        Map<String, Habit> habits = loadHabits();

        if (habits.containsKey(id)) {
            habits.remove(id);
            saveHabits(habits);
        }

    }

    /**
     * Update the habit in our file and habit repo
     * @param habit to update
     */
    @Override
    public void updateHabit(Habit habit) {
        Map<String, Habit> habits = loadHabits();

        if (habits.containsKey(habit.getId())) {
            habits.put(habit.getId(), habit);
            saveHabits(habits);
        }
    }

    /**
     * Loads the map of habits stored in our local file
     * @return map of habits
     */
    private Map<String, Habit> loadHabits() {
        String serializedHabits = fileHandler.loadFileAsString("testSaveFileForHabits.txt");

        if (serializedHabits.isEmpty() || serializedHabits == null) {
            return new HashMap<>();
        } else {
            return getGson().fromJson(serializedHabits, new TypeToken<Map<String, Habit>>() {}.getType());
        }
    }

    /**
     * Loads the map of habitEvents stored in our local file
     * @return map of habitEvents
     */
    private Map<String, HabitEvent> loadHabitEvents() {
        String serializedHabitEvents = fileHandler.loadFileAsString("testSaveFileForHabitEvents.txt");

        if (serializedHabitEvents.isEmpty() || serializedHabitEvents == null) {
            return new HashMap<>();
        } else {
            return getGson().fromJson(serializedHabitEvents, new TypeToken<Map<String, HabitEvent>>() {}.getType());
        }
    }

    /**
     * Saves habitEvents to our local file
     * @param habitEventMap map of habitEvents to save
     */
    private void saveHabitEvents(Map<String, HabitEvent> habitEventMap) {
        String serializedHabitEvents = getGson().toJson(habitEventMap);
        fileHandler.saveStringToFile("testSaveFileForHabitEvents.txt", serializedHabitEvents);
    }

    /**
     * Saves habits to our local file
     * @param habitMap map of habits to save
     */
    private void saveHabits(Map<String, Habit> habitMap) {
        String serializedHabits = getGson().toJson(habitMap);
        fileHandler.saveStringToFile("testSaveFileForHabits.txt", serializedHabits);
    }

    /**
     * Gets gson version of a string
     * @return
     */
    private Gson getGson() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();
    }
}
