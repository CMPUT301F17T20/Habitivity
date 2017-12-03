/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */

package main.habitivity.habits;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Dictionary;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import static org.apache.commons.lang3.ObjectUtils.max;

/**
 * Habit Model Class
 */
public class Habit {
    private String userName;
    private String id;
    private String title;
    private Date startDate;
    private String Reason;
    private List<Integer> daysOfTheWeekToComplete = new ArrayList<>();
    private List<HabitEvent> completions = new ArrayList<>();
    private String habitType;
    private Date lastComplete = null;
    private int onSchedCount = 0;
    private int passedDayCount = 0;
    private int fakeAddDays = 0;
    private static String TAG = "addEvent"; //testing

    public Habit() {
    }

    /**
     * Gets the name of the user which this habit belongs to.
     * @return - the userName of which this habit belongs to
     */
    public String getUserName(){
        return this.userName;
    }

    /**
     * Sets the userName of the user. I.e who does this habit belong to
     * @param name - name of the user
     */
    public void setUserName(String name){
        this.userName = name;
    }

    /**
    * Gets the id of the Habit
    *
    * @return id - id of the Habit
    */
    public String getId() {
        return id;
    }

    /**
    * Sets the id of the Habit
    *
    * @param[in] id - id of the Habit
    */
    public void setId(String id) {
        this.id = id;
    }

    /**
    * Gets the habitType of the Habit
    *
    * @return habitType - habitType of the habit
    */
    public String getHabitType() {
        return habitType;
    }

    /**
    * Sets the habitType of the Habit
    *
    * @param[in] habitType - habitType of the Habit
    */
    public void setHabitType(String habitType) {
        this.habitType = habitType;
    }

    /**
     * Gets the habit event with the corresponding Id
     * @param[in] completionId
     * @return habit event with the corresponding id
     */
    public HabitEvent getHabitEvent(String completionId) {
        for (HabitEvent habitEvent: this.getCompletedEvents()) {
            if (completionId.equals(habitEvent.getId())) {
                return habitEvent;
            }
        }

        return null;
    }

    /**
    * Gets the title of the Habit
    *
    * @return title - title of the Habit
    */
    public String getTitle() {
        return title;
    }

    /**
    * Sets the title of the Habit
    *
    * @param[in] title - title of the Habit
    */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * gets the start date of the habit
     * @return - start date of the habit
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
    * Sets the startDate of the Habit
    *
    * @param[in] startDate - startDate of the Habit
    */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
    * Gets the days of the week where we want to complete the habit.
    * Days of the week are represented by the integers i.e [1,2,3,4,5,6,7] where
    * 1 represents Monday, 2 represents Tuesday, ..... , 7 represents Sunday.
    *
    * @return daysOfTheWeekToComplete - list of integers that represents the days of the week to complete habit on
    */
    public List<Integer> getDaysOfTheWeekToComplete() {
        return daysOfTheWeekToComplete;
    }

    /**
     * Checks the days of week to complete
     * @param day
     * @return
     */
    public Boolean checkDay(int day){
        /** Enter 1-7 value of weekday, return true if in DaysofTheWeekToComplete
         *  !!! Replace with Boolean array to make this a simple return.
         * **/
        for (int i = 0; i < daysOfTheWeekToComplete.size(); i++) {
            if (daysOfTheWeekToComplete.get(i) == day){
                return true;
            }
        }
        return false;

    }

    /**
     * Sets the days of the week where we want to complete the habit
     * i.e if we schedule the habit to be completed on mondays and wednesday then
     * the array will contain [1,3]. Days of the week are represented by integers.
     *
     * @param[in] daysToComplete - list of integers contains the days of the week we want to complete the habit
     *
     */
    public void setDaysOfTheWeekToComplete(List<Integer> daysToComplete) {
        this.daysOfTheWeekToComplete = daysToComplete;

        //this.prevSchedules.put(new Date(), daysToComplete);

    }

    /**
    * Gets our list of habitEvents
    *
    * @return completions - list of habitEvents
    */
    public List<HabitEvent> getCompletedEvents() {
        return completions;
    }

    /**
    * Sets the list of habits that are completed (i.e they're now habit events). 
    * I.e replace our old list of habit events with our new one
    *
    * @param[in] completions - list of habit events
    */
    public void setCompletedEvents(List<HabitEvent> completions) {
        this.completions = completions; /**forced type for my build, delete if necessary obviously  (ListHabitEvent) **/
    }

    /**
    * Add the completed habitEvent to our list of habitEvents 
    *
    * @param[in] habitEvent - event to add to our list of completed HabitEvents
    */
    public void addHabitEvent(HabitEvent habitEvent){
        Calendar compCal = Calendar.getInstance();
        Calendar todayCal = Calendar.getInstance();
        Calendar startCal = Calendar.getInstance();

        //Log.d(TAG, "addHabitEvent");
        //Getting completion date and start date to check if done on schedule today with todayCal
        compCal.setTime(habitEvent.getCompletionDate());
        startCal.setTime(this.getStartDate());

        //Completed today?
        boolean sameDay = compCal.get(Calendar.YEAR) == todayCal.get(Calendar.YEAR) &&
                compCal.get(Calendar.DAY_OF_YEAR) == todayCal.get(Calendar.DAY_OF_YEAR);

        //Completed after the start date?
        boolean afterStart = compCal.get(Calendar.YEAR) == startCal.get(Calendar.YEAR) &&
                compCal.get(Calendar.DAY_OF_YEAR) >= startCal.get(Calendar.DAY_OF_YEAR)
                || compCal.get(Calendar.YEAR) > startCal.get(Calendar.YEAR);

        //if those and on schedule, then done on time!!!
        if ( this.checkDay(compCal.DAY_OF_WEEK) && sameDay && afterStart){
            this.onSchedCount += 1;
            habitEvent.setOnSched(true);
        }

        this.completions.add(habitEvent);
    }

    public int getFakeAddDays() { return this.fakeAddDays; }

    public int getPassedDayCount() { return this.passedDayCount; }

    public void addPassedDayCount(Date start, Date end, Boolean real){
        Calendar endCal = Calendar.getInstance();
        Calendar curCal = Calendar.getInstance();
        Calendar lastCal = Calendar.getInstance();
        Boolean beforeEnd = true;
        int days = 0;
        Double yDays;
        int weeks;
        int remaindays;
        this.fakeAddDays = days;

        if(this.lastComplete != null){
            lastCal.setTime(this.lastComplete);
            lastCal.add(Calendar.DATE, 1);
        }
        else{
            lastCal.setTime(start);
        }
        endCal.setTime(end);
        //Choose user inputed start from previous login
        curCal.setTime(max(start,this.startDate, lastCal.getTime()));
        beforeEnd = curCal.get(Calendar.YEAR) == endCal.get(Calendar.YEAR) &&
                curCal.get(Calendar.DAY_OF_YEAR) < endCal.get(Calendar.DAY_OF_YEAR)
                || curCal.get(Calendar.YEAR) < endCal.get(Calendar.YEAR);
        if (beforeEnd){
            yDays = 365.25*(endCal.get(Calendar.YEAR) - curCal.get(Calendar.YEAR));
            weeks = (endCal.get(Calendar.DAY_OF_YEAR) - curCal.get(Calendar.DAY_OF_YEAR) + yDays.intValue() - 1 ) / 7;
            remaindays = (endCal.get(Calendar.DAY_OF_YEAR) - curCal.get(Calendar.DAY_OF_YEAR) + yDays.intValue() - 1 )%7;
            //Use count in schedule to get days missed in weeks
            days = weeks*this.daysOfTheWeekToComplete.size();
            //Go over scheduled days, count scheduled days within remaining days
            for ( int i = 0; i < this.daysOfTheWeekToComplete.size(); i++){
                if ( ((this.daysOfTheWeekToComplete.get(i)-curCal.DAY_OF_WEEK)%7) < remaindays){
                    days += 1;
                }
            }

        }
        this.fakeAddDays = days;
        if (real){ this.passedDayCount += days; }

    }
    /**
    * Remove the habitEvent from our list of habitEvents 
    *
    * @param[in] habitEvent - event to remove from our list of completed HabitEvents
    */
    public void deleteHabitEvent(HabitEvent habitEvent){
        // This may not remove the HabitEvent correctly in all cases.
        // For full coverage we can remove manually by habitEvent.id, though this will be less efficient.
        this.completions.remove(habitEvent);
    }

    /**
    * Gets the reason of the habit
    *
    * @return Reason - the reason for the habit
    */
    public String getReason() {
        return Reason;
    }

    /**
    * Sets the reason for the habit
    *
    * @param[in] reason - reason for the habit
    */
    public void setReason(String reason) {
        Reason = reason;
    }

    /**
     * Get last completed event
     * @return last completed event
     */
    public Date getLastComplete() {
        return lastComplete;
    }

    /**
     * Set the date of the last completed event
     * @param lastComplete data of last completed event
     */
    public void setLastComplete(Date lastComplete) {
        this.lastComplete = lastComplete;
    }

    public void addOnSchedCount() {
        onSchedCount += 1;
    }

    public int getOnSchedCount() {
        return onSchedCount;
    }

    @Override
    /**
     * ArrayAdapter calls toString() on the object and displays it on the TextView. Override to display what we want
     */
    public String toString(){
        return "Habit Title: " + this.getTitle() + "| UserName: " + this.getUserName();

    }
}
