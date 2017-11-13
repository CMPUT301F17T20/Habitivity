package main.habitivity.habits;

import java.util.Date;

/**
 * Created by Shally on 2017-11-13.
 */

public class HabitSingletonContainer {
    private static final HabitSingletonContainer ourInstance = new HabitSingletonContainer();

    private Habit currentHabit = new Habit();
    private HabitEvent currentHabitEvent = new HabitEvent(new Date());
    public static HabitSingletonContainer getInstance() {
        return ourInstance;
    }

    public void setHabit(Habit habit){
        this.currentHabit = habit;
    }

    public void setHabitEvent(HabitEvent habitEvent){
        this.currentHabitEvent = habitEvent;
    }

    public Habit getHabit(){
        return this.currentHabit;
    }

    public HabitEvent getHabitEvent(){return this.currentHabitEvent;}

    private HabitSingletonContainer() {
    }
}
