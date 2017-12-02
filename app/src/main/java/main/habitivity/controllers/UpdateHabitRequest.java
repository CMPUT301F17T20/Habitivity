package main.habitivity.controllers;

import java.util.Date;
import java.util.List;

import main.habitivity.habits.Habit;
/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */

/**
 * An object that encapsulates all the components required to
 * create a new habit. Helper function for AddHabitController
 */
public class UpdateHabitRequest {
    private String id;
    private Habit oldId;
    private String reason;
    private Date startDate;
    private String habitType;
    private Habit oldHabit;
    private List<Integer> daysOfTheWeek;

    public void setOldHabit(Habit habit){
        this.oldHabit = habit;
    }
    public Habit getOldHabit(){
        return this.oldHabit;
    }
    /**
     * Sets the id of the old habit (i.e habit before editing). This is mainly used for when the user is editing
     * the habit and changes the title since we're using the title as the habit's unique identifier
     * @param id - id of the old habit
     */
    public void setOldId(Habit id) {
        this.oldId = id;
    }

    /**
     * Gets the id of the old habit (i.e habit before editing). This is mainly used for when the user is editing
     * the habit and changes the title since we're using the title as the habit's unique identifier
     * @return - the id of the old habit
     */
    public Habit getOldId() {
        return oldId;
    }

    /**
     * Get the id of the habit
     * @return id of the habit
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the habit type
     * @param type - type to set the habit to
     */
    public void setHabitType(String type){
        this.habitType = type;
    }

    /**
     * Returns the habit type
     * @return
     */
    public String getHabitType(){
        return this.habitType;
    }

    /**
     * Sets the id of the habit
     * @param id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the reason of the habit
     * @return reason of the habit
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the reason of the habit
     * @param reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * Get the startDate of the habit
     * @return startDate of the habit
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the startDate of the habit
     * @param - date to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Get the days of the week the habit reoccurs on
     * @return days of the week the habit reoccurs on
     */
    public List<Integer> getDaysOfTheWeek() {
        return daysOfTheWeek;
    }

    /**
     * Sets the days of the week for the habits to reoccur on
     * @param - days of the week to reoccur
     */
    public void setDaysOfTheWeek(List<Integer> daysOfTheWeek) {
        this.daysOfTheWeek = daysOfTheWeek;
    }
}