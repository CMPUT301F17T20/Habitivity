/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */

package main.habitivity.habits;

import android.location.Location;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import main.habitivity.services.LocalHabitService;
import main.habitivity.services.WhichHabitService;
import main.habitivity.users.User;
import main.habitivity.users.UserContainer;

/*
 * This class implements IHabitRepository and provides an in memory store
 * of habits.
 */
public class HabitRepository implements IHabitRepository{
    private WhichHabitService habitService;
    private ArrayList<Habit> habits;
    private Map<String, Location> habitLocations;
    private ArrayList<HabitEvent> habitEvents;
    private String userID;
    private String ID;

    /**
     * Get the userId of the currently logged in user
     * @return userId of logged in user
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets the userId of the currently logged in user
     * @return userId to change user to
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public HabitRepository(WhichHabitService habitService) {
        this.habitService = habitService;
        this.habits = new ArrayList<Habit>();
        this.habitEvents = new ArrayList<HabitEvent>();
        this.habitLocations = new HashMap<>();
    }

    /**
     * Gets the list of sorted habits
     *
     * @return list of sorted habits
     */
    public ArrayList<Habit> getHabits() {
        //ensureHabits();
        return getSortedHabits();
    }

    public void setHabits(ArrayList<Habit> habits){
        for(Habit habit: habits){
            this.habits.add(habit);
        }
    }

    public void setHabitEvents(ArrayList<HabitEvent> habitEvents){
        for(HabitEvent habitEvent: habitEvents){
            this.habitEvents.add(habitEvent);
        }
    }

    /**
     * Get a list of habit events
     * @return list of habit events
     */
    public List<HabitEvent> getHabitEvents() {
        //ensureHabitEvents();
        List<HabitEvent> events = new ArrayList<>(habitEvents);
        return events;
    }

    public Map<String, Location> ensureFriendsLocations(){
        Map<String, Location> friendsLocations = new HashMap<>();
        User currentUser = UserContainer.getInstance().getUser();
        //add habit events of other users
        for(User user: currentUser.getFollowing()){
            for(HabitEvent habitEvent: user.getHabitEvents()){
                if(habitEvent.getLocation() != null){
                    friendsLocations.put(habitEvent.getId(), habitEvent.getLocation());
                    habitLocations.put(habitEvent.getId(), habitEvent.getLocation());
                }
            }
        }
        return friendsLocations;
    }

    public Map<String, Location> ensureMyLocations(){
        Map<String, Location> myLocations = new HashMap<>();
        User currentUser = UserContainer.getInstance().getUser();
        //add habit events of other users
        for(HabitEvent habitEvent: currentUser.getHabitEvents()){
            if(habitEvent.getLocation() != null){
                myLocations.put(habitEvent.getId(), habitEvent.getLocation());
                habitLocations.put(habitEvent.getId(), habitEvent.getLocation());
            }
        }
        return myLocations;
    }
    /**
     * Grabs habit events locations that are saved locally since we don't have elastic search yet
     */
    public void ensureHabitLocations(){
        ensureMyLocations();
        ensureFriendsLocations();
    }

    public List<Location> getListOfMyHabitLocations(){
        List<Location> location = new ArrayList<>( ensureMyLocations().values());
        return location;
    }

    public List<Location> getListOfFriendsHabitLocations(){
        List<Location> location = new ArrayList<>( ensureFriendsLocations().values());
        return location;
    }
    /**
     * Get a list of habit locations
     * @return list of habit locations
     */
    public List<Location> getHabitLocations() {
        ensureHabitLocations();
        List<Location> location = new ArrayList<>(habitLocations.values());
        return location;
    }

    /**
     * Gets the habit of the with the corresponding id
     * @param[in] - id of habit
     * @return habit with the corresponding id
     */
    public Habit getHabit(String id) {
        return new Habit();
    }

    /**
     * Update the habitEvent in our local disk/server
     *
     * @param[in] - the habit to update in our local disk/server
     */
    public void updateHabitEvent(HabitEvent oldHabitEvent, HabitEvent newHabitEvent){
        //ensureHabitEvents();

        //Use this to set on schedule variables in the event and it's habit //******newHabitEvent = this.setOnSchedule(newHabitEvent);
        habitService.updateHabitEventRequest(oldHabitEvent, newHabitEvent);
        int index = habitEvents.indexOf(oldHabitEvent);
        habitEvents.set(index, newHabitEvent);
    }

    /**
     * Add the habitEvent in our local disk/server
     *
     * @param[in] - the habit to update in our local disk/server
     */
    public void addHabitEvent(HabitEvent habitEvent){
       // ensureHabitEvents();

        // Use this to set on schedule variables in the event and it's habit //********habitEvent = this.setOnSchedule(habitEvent);
        habitService.addHabitEventRequest(habitEvent);
        habitEvents.add(habitEvent);
    }

    /**
     * Remove the habitEvent in our local disk/server
     *
     * @param[in] - the habit to update in our local disk/server
     */
    public void removeHabitEvent(HabitEvent event){
        habitService.deleteHabitEventRequest(event);
        habitEvents.remove(event);
    }

    /**
     * Grabs habits that are saved locally
     */
    private void ensureHabits() {
        if (habits.isEmpty()) {
            for (Habit habit: habitService.getHabits()) {
                habits.add(habit);
            }
        }
    }

    /**
     * Updates the event as 'on schedule', habit on schedule count,
     * and last completed on schedule for habit
     *
     * @author Nicolas Parada
     * @version 1.0
     * @since 1.0
     * @param addupdate
     * @return addupdate with true parameter for variable onSched if on schedule
     */
    private HabitEvent setOnSchedule(HabitEvent addupdate){
        /*Habit eventHabit;
        Calendar compCal = Calendar.getInstance();
        Calendar todayCal = Calendar.getInstance();
        Calendar startCal = Calendar.getInstance();

        //Getting completion date of habit event
        compCal.setTime(addupdate.getCompletionDate());

        for (Habit habit: habitService.getHabits()) {
            if (habit.getId() == addupdate.getId()) {
                eventHabit = habit;
                //Getting start date of habit
                startCal.setTime(eventHabit.getStartDate());

                //Completed today? Used to check if done on schedule today with todayCal
                boolean sameDay = compCal.get(Calendar.YEAR) == todayCal.get(Calendar.YEAR) &&
                        compCal.get(Calendar.DAY_OF_YEAR) == todayCal.get(Calendar.DAY_OF_YEAR);

                //Completed after the start date?
                boolean afterStart = compCal.get(Calendar.YEAR) == startCal.get(Calendar.YEAR) &&
                        compCal.get(Calendar.DAY_OF_YEAR) >= startCal.get(Calendar.DAY_OF_YEAR)
                        || compCal.get(Calendar.YEAR) > startCal.get(Calendar.YEAR);

                //if those and on schedule, then done on time!!!
                if ( eventHabit.checkDay(compCal.DAY_OF_WEEK) && sameDay && afterStart){
                    //This event is on schedule
                    addupdate.setOnSched(true);
                    //On Schedule days Count increased by today and the habits last complete set
                    eventHabit.setLastComplete(compCal.getTime());
                    eventHabit.incrementOnSchedCount();
                    //Update this Habit's values
                    updateHabit(habit, eventHabit);
                }
                break;
            }
        }*/
        return addupdate;
    }

    /**
     * Grabs all the habitEvent that are saved locally
     */
    private void ensureHabitEvents() {
//        if (habitEvents.isEmpty()) {
//            for (HabitEvent habitEvent: habitService.getHabitEvents()) {
//                habitEvents.put(habitEvent.getId(), habitEvent);
//            }
//        }
    }

    /**
     * Update the habit stored in our local disk/server
     *
     * @param[in] - the habit to update in our local disk/server
     *
     * NOTE: YOU'RE GONNA NEED THE WHICHSERVICE CLASS TO BE COMPLETED SO IGNORE THIS FOR NOW
     * AND I'LL TAKE CARE OF IT WHEN I FINISH SERVER/LOCAL DISK CLASSES
     */
    public void updateHabit(Habit oldHabit, Habit newHabit) {
        //ensureHabits();
        habitService.updateHabitRequest(oldHabit, newHabit);
        int index = habits.indexOf(oldHabit);
        habits.set(index, newHabit);
    }

    /**
     * Add the habit to our local disk/server
     *
     * @param[in] - the habit to add in our local disk/server
     *
     * NOTE: YOU'RE GONNA NEED THE WHICHSERVICE CLASS TO BE COMPLETED SO IGNORE THIS FOR NOW
     * AND I'LL TAKE CARE OF IT WHEN I FINISH SERVER/LOCAL DISK CLASSES
     */
    public void addHabit(Habit habit) {
        //ensureHabits();

        habitService.addHabitRequest(habit);
        habits.add(habit);
    }

    /**
     * Remove the habit of the corresponding id from our local disk/server
     *
     * @param[in] - id of the habit to remove
     *
     * NOTE: YOU'RE GONNA NEED THE WHICHSERVICE CLASS TO BE COMPLETED SO IGNORE THIS FOR NOW
     * AND I'LL TAKE CARE OF IT WHEN I FINISH SERVER/LOCAL DISK CLASSES
     */
    public void removeHabit(Habit habit) {
        //ensureHabits();
        habitService.deleteHabitRequest(habit);
        habits.remove(habit);
    }

    /**
     * Sorts the habit by creation date
     *
     * @return a list of sorted habits
     */
    private ArrayList<Habit> getSortedHabits() {

        ArrayList<Habit> sortedHabits = new ArrayList<>(habits);
        //TODO LATER
        //Collections.sort(sortedHabits, reverseChronologicalHabitComparator);

        return sortedHabits;
    }

    public ArrayList<HabitEvent> getSortedEvents() {
        ArrayList<HabitEvent> sortedEvents = new ArrayList<>(habitEvents);

        Collections.sort(sortedEvents, reverseChronologicalEventComparator);

        return sortedEvents;
    }

    /**
     * Helper function to compare habits and sort them
     *
     * TODO LATER
     */
    private static Comparator<Habit> reverseChronologicalHabitComparator  = new Comparator<Habit>() {
        @Override
        public int compare(Habit lhs, Habit rhs) {
            return rhs.getStartDate().compareTo(lhs.getStartDate());
        }
    };

    private static Comparator<HabitEvent> reverseChronologicalEventComparator  = new Comparator<HabitEvent>() {
        @Override
        public int compare(HabitEvent lhs, HabitEvent rhs) {
            return rhs.getCompletionDate().compareTo(lhs.getCompletionDate());
        }
    };

    /**
     * Gets the number of habits in our habit repo
     * @return number of habits stored
     */
    private int getHabitCount() {return this.getSortedHabits().size(); }


}