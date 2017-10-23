package main.habitivity.habits;

import java.util.List;

import main.habitivity.observables.IObservable;

public interface IHabitRepository extends IObservable<List<Habit>> {
    Habit getHabit(String id);
    List<Habit> getHabits();

    void removeHabit(String id);
    void addHabit(Habit habit);
    void updateHabit(Habit habit);

}
