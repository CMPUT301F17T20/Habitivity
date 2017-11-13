package main.habitivity.habits;

/**
 * Created by Shally on 2017-11-13.
 */

public class HabitSingletonContainer {
    private static final HabitSingletonContainer ourInstance = new HabitSingletonContainer();

    private Habit currentHabit = new Habit();

    public static HabitSingletonContainer getInstance() {
        return ourInstance;
    }

    public void setHabit(Habit habit){
        this.currentHabit = habit;
    }

    public Habit getHabit(){
        return this.currentHabit;
    }

    private HabitSingletonContainer() {
    }
}
