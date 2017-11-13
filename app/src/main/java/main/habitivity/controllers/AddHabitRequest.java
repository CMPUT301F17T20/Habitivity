package main.habitivity.controllers;

import java.util.Date;
import java.util.List;
/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */

/**
 * An object that encapsulates all the components required to
 * create a new habit. Helper function for AddHabitController
 */
public class AddHabitRequest {
    private String id;
    private String reason;
    private Date startDate;
    private List<Integer> daysOfTheWeek;

    /**
     * Get the id of the habit
     * @return id of the habit
     */
    public String getId() {
        return id;
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