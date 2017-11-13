/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */

package main.habitivity.habits;

import java.util.Date;

/**
 * This is a singleton class used to determine the current habit or the current habitEvent that
 * we have selected/edited
 */
public class HabitSingletonContainer {
    private static final HabitSingletonContainer ourInstance = new HabitSingletonContainer();

    private Habit currentHabit = new Habit();
    private HabitEvent currentHabitEvent = new HabitEvent(new Date());


    public static HabitSingletonContainer getInstance() {
        return ourInstance;
    }

    /**
     * Set the habit to our singleton class
     * @param habit to set
     */
    public void setHabit(Habit habit){
        this.currentHabit = habit;
    }

    /**
     * Set the habitEvent to our singleton class
     * @param habitEvent to set
     */
    public void setHabitEvent(HabitEvent habitEvent){
        this.currentHabitEvent = habitEvent;
    }

    /**
     * Gets the current habit
     * @return the current habit
     */
    public Habit getHabit(){
        return this.currentHabit;
    }

    /**
     * Gets the current habit event
     * @return the current habit event
     */
    public HabitEvent getHabitEvent(){return this.currentHabitEvent;}

    private HabitSingletonContainer() {
    }
}
