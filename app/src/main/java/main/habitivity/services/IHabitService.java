/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */
package main.habitivity.services;

import java.util.List;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;

/**
 * Interface for the habitServices (Provide encapsulation)
 */
public interface IHabitService {
    List<Habit> getHabits();

    void addHabit(Habit habit);
    void deleteHabit(String id);
    void updateHabit(Habit habit);

    List<HabitEvent> getHabitEvents();

    void addHabitEvent(HabitEvent habitEvent);
    void deleteHabitEvent(String id);
    void updateHabitEvent(HabitEvent habitEvent);
}
