/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */

package main.habitivity.habits;

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
import java.util.TreeMap;

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
    private TreeMap<Date, List<Integer>> prevSchedules = new TreeMap<>(Collections.<Date>reverseOrder());
    private List<Date> curCompEvents;

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
     */
    public void setDaysOfTheWeekToComplete(List<Integer> daysToComplete) {
        this.daysOfTheWeekToComplete = daysToComplete;
        this.prevSchedules.put(new Date(), daysToComplete);

    }

    public int[] getChartCount(){
        Date today = new Date();
        Date endEdit;
        Date startEdit;
        //completed and incompleted count, add days off schedule?
        int[] compIncompCount = {0,0};
        Boolean isComplete;
        Calendar week = Calendar.getInstance();
        Calendar day = Calendar.getInstance();
        Calendar event = Calendar.getInstance();
        Set editKeys = prevSchedules.entrySet();
        Iterator it = editKeys.iterator();
        this.curCompEvents = new ArrayList<Date>();
        //See if events on schedules, adding completed events so far.
        for (int i = 0; i < completions.size(); i++){
            curCompEvents.add(completions.get(i).getCompletionDate());
        }
        //initializing the end value for each looped schedule section:
        endEdit = today;
        while(it.hasNext()){
            Map.Entry cur = (Map.Entry) it.next();
            //skip repeat edits
            if (cur.getKey() == ((Map.Entry) it.next()).getKey()){
                continue;
            }

            startEdit = (Date) cur.getKey();
            //If edit below start date stop counting events
            if ( ( startEdit.compareTo(this.getStartDate()) < 0 ) ){
                startEdit = this.getStartDate();
            }
            ArrayList<Integer> schedule = (ArrayList<Integer>) cur.getValue();
            week.setTime(startEdit);
            week.set(Calendar.HOUR_OF_DAY, 0);
            week.set(Calendar.MINUTE, 0);
            week.set(Calendar.SECOND, 0);
            week.set(Calendar.MILLISECOND, 0);

            outerLoop:
            while(true){
                for (int j = 0; j < schedule.size(); j++) {
                    //May need to increase first week of edit by one day?
                    for (int i = 0; i <= 7 - week.DAY_OF_WEEK; i++) {
                        day = week;
                        //Checking if this day of the week  in this current schedule:
                        if (week.DAY_OF_WEEK + i == schedule.get(j)) {
                            isComplete = false;
                            day.add(Calendar.DATE, i);
                            //Inclusive to day when changed to new schedule--change edit to start +1 day?
                            if ( ( day.getTime().compareTo(endEdit) > 0 ) ){
                                break outerLoop;
                            }
                            //Checking if this scheduled day was completed correctly add count:
                            for (int k = curCompEvents.size() - 1; k >= 0; k--) {
                                event.setTime(curCompEvents.get(k));
                                event.set(Calendar.HOUR_OF_DAY, 0);
                                event.set(Calendar.MINUTE, 0);
                                event.set(Calendar.SECOND, 0);
                                event.set(Calendar.MILLISECOND, 0);
                                if (event.getTime().compareTo(day.getTime()) == 0) {
                                    compIncompCount[0] += 1;
                                    isComplete = true;
                                    curCompEvents.remove(k);
                                }
                            }
                            //If this scheduled day was not found as event, incomplete then:
                            if (!isComplete){
                                compIncompCount[1] += 1;
                            }

                        }
                    }
                }
                week.add(Calendar.DATE, 8 - week.DAY_OF_WEEK );
                //Inclusive to day when changed to new schedule--change edit to start +1 day?
                if ( ( week.getTime().compareTo(endEdit) > 0 ) ){
                    break outerLoop;
                }
            }
            endEdit = startEdit;
            //If edit below start date stop counting events
            if ( ( startEdit.compareTo(this.getStartDate()) <= 0 ) ){
                break;
            }
        }
        //compIncompCount[0] = 15;
        //compIncompCount[1] = 9;//TESTLINE;
        return compIncompCount;
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

    @Override
    /**
     * ArrayAdapter calls toString() on the object and displays it on the TextView. Override to display what we want
     */
    public String toString(){
        return "Habit Title: " + this.getTitle() + "| UserName: " + this.getUserName();

    }
}
