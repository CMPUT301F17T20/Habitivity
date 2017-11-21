/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */

package main.habitivity.habits;

import android.location.Location;

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

    void removeHabit(String id);
    void addHabit(Habit habit);
    void updateHabit(Habit habit);

    void updateHabitEvent(HabitEvent habitEvent);
    void removeHabitEvent(String id);
    void addHabitEvent(HabitEvent habitEvent);

}
