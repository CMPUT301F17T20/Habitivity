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
import main.habitivity.R;

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
    private int onSchedCount;
    private int passedDayCount;
    private int fakeAddDays;
    private int timesCompleted;

    public Habit() {
        timesCompleted = 0;
        onSchedCount = 0;
        passedDayCount = 0;
        fakeAddDays = 0;
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

    public int getTimesCompleted(){
        return timesCompleted;
    }

    public void setTimesCompleted(int times){
        timesCompleted = times;
    }

    public void incrementTimesCompleted(){
        timesCompleted++;
    }

    public int getLevelIcon(){
        if (timesCompleted < 5){
            return R.drawable.level_icon_bronze;
        }else if (timesCompleted < 15){
            return R.drawable.level_icon_silver;
        }else{
            return R.drawable.level_icon_gold;
        }
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
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);
        startCal.set(Calendar.HOUR_OF_DAY, 0);
        this.startDate = startCal.getTime();
    }

    /**
     * This method checks if a date a user  inputs is after the designated startdate.
     *
     * @author Nicolas Parada
     * @version 1.0
     * @see Habit
     * @since 1.0
     * @param inDate
     * @return After startdate Boolean
     */
    public boolean afterStartDate(Date inDate){
        return this.startDate.compareTo(inDate) <= 0;
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
     * Checks if a weekday is in the days of week to complete
     *
     * @author Nicolas Parada
     * @version 1.0
     * @see Habit
     * @since 1.0
     * @param day
     * @return if on scheduled weekday, true
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
        this.completions.add(habitEvent);
    }

    /**
     * Returns the scheduled days of the week passed since last login when still online
     *
     * @author Nicolas Parada
     * @version 1.0
     * @since 1.0
     */
    public int getFakeAddDays() { return this.fakeAddDays; }

    /**
     * Returns the count of all the scheduled days of the week passed.
     *
     * @author Nicolas Parada
     * @version 1.0
     * @since 1.0
     */
    public int getPassedDayCount() { return this.passedDayCount; }

    /**
     * Adds the scheduled days of the week passed since last login,
     * aka between two user inputed dates, and adds as missed days.
     * Fake days is the the scheduled days of the week passed since last login when still online
     *
     * @author Nicolas Parada
     * @version 1.0
     * @since 1.0
     */
    public void addPassedDayCount(Date start, Date end, Boolean real, ArrayList<Date> skipDays){
        Calendar endCal = Calendar.getInstance();
        Calendar curCal = Calendar.getInstance();
        Calendar skipCal = Calendar.getInstance();
        Boolean beforeEnd;
        Boolean afterStart;
        int days = 0;
        Double yDays;
        int weeks;
        int remaindays;
        this.fakeAddDays = days;

        endCal.setTime(end);
        curCal.setTime(max(start,this.startDate));

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
            for (Date date: skipDays){
                skipCal.setTime(date);
                beforeEnd = skipCal.get(Calendar.YEAR) == endCal.get(Calendar.YEAR) &&
                        skipCal.get(Calendar.DAY_OF_YEAR) < endCal.get(Calendar.DAY_OF_YEAR)
                        || skipCal.get(Calendar.YEAR) < endCal.get(Calendar.YEAR);
                afterStart = curCal.get(Calendar.YEAR) == endCal.get(Calendar.YEAR) &&
                        curCal.get(Calendar.DAY_OF_YEAR) >= endCal.get(Calendar.DAY_OF_YEAR)
                        || curCal.get(Calendar.YEAR) > endCal.get(Calendar.YEAR);
                if (afterStart && beforeEnd && this.checkDay(skipCal.DAY_OF_WEEK)){
                    days -= 1;
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
     * Decrement or increment by one the onSchedule habit events count for this habit.
     *
     * @author Nicolas Parada
     * @version 1.0
     * @since 1.0
     */
    public void incrementOnSchedCount() {
        onSchedCount += 1;
    }

    /**
     * Decrement or increment by one the onSchedule habit events count for this habit.
     *
     * @author Nicolas Parada
     * @version 1.0
     * @since 1.0
     */
    public void decrementOnSchedCount() {
        onSchedCount -= 1;
    }

    /**
     * Return the onSchedule habit events count for this habit.
     *
     * @author Nicolas Parada
     * @version 1.0
     * @since 1.0
     * @return int onSchedCount
     */
    public int getOnSchedCount() {
        return onSchedCount;
    }

    @Override
    /**
     * ArrayAdapter calls toString() on the object and displays it on the TextView. Override to display what we want
     */
    public String toString(){
        return "Habit Title: " + this.getTitle() + " | UserName: " + this.getUserName();

    }
}
