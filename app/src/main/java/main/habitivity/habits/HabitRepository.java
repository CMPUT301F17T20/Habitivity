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

import main.habitivity.observables.IObservable;
import main.habitivity.observables.IObserver;
import main.habitivity.services.LocalHabitService;
import main.habitivity.services.WhichHabitService;

/*
 * This class implements IHabitRepository and provides an in memory store
 * of habits.
 */
public class HabitRepository{
    private LocalHabitService habitService;
    private Map<String, Habit> habits;

    private Set<IObserver<List<Habit>>> observers = new HashSet<>();

    public HabitRepository(LocalHabitService habitService) {
        this.habitService = habitService;
        this.habits = new HashMap<>();
    }

    /**
    * Gets the list of sorted habits 
    * 
    * @return list of sorted habits
    */
    public List<Habit> getHabits() {
        return new ArrayList<Habit>();
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
     * Update the habit stored in our local disk/server 
     *
     * @param[in] - the habit to update in our local disk/server
     *
     * NOTE: YOU'RE GONNA NEED THE WHICHSERVICE CLASS TO BE COMPLETED SO IGNORE THIS FOR NOW
     * AND I'LL TAKE CARE OF IT WHEN I FINISH SERVER/LOCAL DISK CLASSES
     */
    public void updateHabit(Habit habit) {

        habitService.updateHabit(habit);
        habits.put(habit.getId(), habit);
        notifyChange();
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

        habitService.addHabit(habit);
        habits.put(habit.getId(), habit);
        notifyChange();
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

        habitService.deleteHabit(id);
        habits.remove(id);

    }

    /**
     * Sorts the habit by creation date
     *
     * @return a list of sorted habits 
     */
    private List<Habit> getSortedHabits() {
        return new ArrayList<Habit>();
    }

    /**
     * This was originally intended for the observer class. DON'T IMPLEMENT this since we
     * might need to change that part
     */

    /**get an ArrayList of only the habits for today**/
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

    private int getHabitCount() {return this.getSortedHabits().size(); }

    private void notifyChange() {
    }

}
