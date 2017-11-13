package main.habitivity.habits;

import java.util.List;

import main.habitivity.observables.IObservable;

/** 
 * IHabitRepository provides access to habits in memory and abstracts away
 * the type of persistence used to store them
 */
public interface IHabitRepository {
    Habit getHabit(String id);
    List<Habit> getHabits();
    List<HabitEvent> getHabitEvents();

    void removeHabit(String id);
    void addHabit(Habit habit);
    void updateHabit(Habit habit);

    void updateHabitEvent(HabitEvent habitEvent);
    void removeHabitEvent(String id);

    void addHabitEvent(HabitEvent habitEvent);

}
