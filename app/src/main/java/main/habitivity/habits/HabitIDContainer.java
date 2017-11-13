package main.habitivity.habits;

/**
 * Created by Shally on 2017-11-13.
 */

public class HabitIDContainer {
    private static final HabitIDContainer ourInstance = new HabitIDContainer();

    private Habit currentHabit = new Habit();

    public static HabitIDContainer getInstance() {
        return ourInstance;
    }

    public void setHabit(Habit habit){
        this.currentHabit = habit;
    }

    public Habit getHabit(){
        return this.currentHabit;
    }

    private HabitIDContainer() {
    }
}
