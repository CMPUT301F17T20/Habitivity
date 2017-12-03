/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */

package main.habitivity.habits;

import android.location.Location;

import java.util.ArrayList;
import java.util.List;

/** 
 * IHabitRepository provides access to habits in memory and abstracts away
 * the type of persistence used to store them
 */
public interface IHabitRepository {
    Habit getHabit(String id);
    List<Habit> getHabits();
    List<HabitEvent> getHabitEvents();
    List<Location> getHabitLocations();
    List<Location> getListOfMyHabitLocations();
    List<Location> getListOfFriendsHabitLocations();

    void removeHabit(Habit habit);
    void addHabit(Habit habit);
    void updateHabit(Habit oldHabit, Habit habit);
    void setHabits(ArrayList<Habit> habits);

    void updateHabitEvent(HabitEvent oldHabitEvent, HabitEvent newHabitEvent);
    void removeHabitEvent(HabitEvent habitEvent);
    void addHabitEvent(HabitEvent habitEvent);
    void setHabitEvents(ArrayList<HabitEvent> habitEvents);

}
