package main.habitivity.habits;

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

/*
 * This class implements IHabitRepository and provides an in memory store
 * of habits.
 */
public class HabitRepository implements IHabitRepository{
    private LocalHabitService habitService;
    private Map<String, Habit> habits;
    private Map<String, HabitEvent> habitEvents;
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

    public HabitRepository(LocalHabitService habitService) {
        this.habitService = habitService;
        this.habits = new HashMap<>();
        this.habitEvents = new HashMap<>();
    }

    /**
    * Gets the list of sorted habits 
    * 
    * @return list of sorted habits
    */
    public ArrayList<Habit> getHabits() {
        ensureHabits();
        return getSortedHabits();
    }

    /**
     * Get a list of habit events
     * @return list of habit events
     */
    public List<HabitEvent> getHabitEvents() {
        ensureHabitEvents();
        List<HabitEvent> events = new ArrayList<>(habitEvents.values());
        return events;
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
    public void updateHabitEvent(HabitEvent habitEvent){
        ensureHabitEvents();
        habitService.updateHabitEvent(habitEvent);
        habitEvents.put(habitEvent.getId(), habitEvent);
    }

    /**
     * Add the habitEvent in our local disk/server
     *
     * @param[in] - the habit to update in our local disk/server
     */
    public void addHabitEvent(HabitEvent habitEvent){
        ensureHabitEvents();
        habitService.addHabitEvent(habitEvent);
        habitEvents.put(habitEvent.getId(), habitEvent);
    }

    /**
     * Remove the habitEvent in our local disk/server
     *
     * @param[in] - the habit to update in our local disk/server
     */
    public void removeHabitEvent(String id){
        ensureHabitEvents();
        habitService.deleteHabitEvent(id);
        habitEvents.remove(id);
    }

    /**
     * Grabs habits that are saved locally
     */
    private void ensureHabits() {
        if (habits.isEmpty()) {
            for (Habit habit: habitService.getHabits()) {
                habits.put(habit.getId(), habit);
            }
        }
    }

    /**
     * Grabs all the habitEvent that are saved locally
     */
    private void ensureHabitEvents() {
        if (habitEvents.isEmpty()) {
            for (HabitEvent habitEvent: habitService.getHabitEvents()) {
                habitEvents.put(habitEvent.getId(), habitEvent);
            }
        }
    }

    /**
     * Update the habit stored in our local disk/server 
     *
     * @param[in] - the habit to update in our local disk/server
     *
     * NOTE: YOU'RE GONNA NEED THE WHICHSERVICE CLASS TO BE COMPLETED SO IGNORE THIS FOR NOW
     * AND I'LL TAKE CARE OF IT WHEN I FINISH SERVER/LOCAL DISK CLASSES
     */
    public void updateHabit(Habit habit) {
        ensureHabits();
        habitService.updateHabit(habit);
        habits.put(habit.getId(), habit);
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
        ensureHabits();

        habitService.addHabit(habit);
        habits.put(habit.getId(), habit);
    }

    /**
     * Remove the habit of the corresponding id from our local disk/server
     *
     * @param[in] - id of the habit to remove
     *
     * NOTE: YOU'RE GONNA NEED THE WHICHSERVICE CLASS TO BE COMPLETED SO IGNORE THIS FOR NOW
     * AND I'LL TAKE CARE OF IT WHEN I FINISH SERVER/LOCAL DISK CLASSES
     */
    public void removeHabit(String id) {
        ensureHabits();
        habitService.deleteHabit(id);
        habits.remove(id);

    }

    /**
     * Sorts the habit by creation date
     *
     * @return a list of sorted habits 
     */
    private ArrayList<Habit> getSortedHabits() {

       ArrayList<Habit> sortedHabits = new ArrayList<>(habits.values());
       //TODO LATER
       //Collections.sort(sortedHabits, reverseChronologicalHabitComparator);

        return sortedHabits;
    }

    public ArrayList<HabitEvent> getSortedEvents() {
        ArrayList<HabitEvent> sortedEvents = new ArrayList<>(habitEvents.values());

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
     * get an ArrayList of only the habits for today
     * @return arraylist of todays habits
     */
    public List<Habit> getTodaysHabits() {
        List<Habit> Today = new ArrayList<>();
        Calendar weekDay = Calendar.getInstance(); //is this correct

        for (int i=0; i < habits.size(); i++) {
            /**
             * if a habit is for this day of the week add it to today's Habits
             */
            Habit habit = habits.get(i);
            if ( habit.checkDay(weekDay.get(Calendar.DAY_OF_WEEK) ) && ( habit.getStartDate().compareTo(weekDay.getTime()) >= 0 ) ) {
                Today.add(habit);
            }
        }
        return Today;
    }

    /**
     * Gets the number of habits in our habit repo
     * @return number of habits stored
     */
    private int getHabitCount() {return this.getSortedHabits().size(); }


}
