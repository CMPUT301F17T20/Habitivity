package main.habitivity.habits;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import main.habitivity.observables.IObservable;
import main.habitivity.observables.IObserver;
import main.habitivity.services.WhichHabitService;

public class HabitRepository{
    private WhichHabitService habitService;
    private Map<String, Habit> habits;

    private Set<IObserver<List<Habit>>> observers = new HashSet<>();

    public HabitRepository(WhichHabitService habitService) {
    }

    public List<Habit> getHabits() {
        return new ArrayList<Habit>();
    }

    public Habit getHabit(String id) {
        return new Habit();
    }

    public void updateHabit(Habit habit) {
    }

    public void addHabit(Habit habit) {
    }


    public void removeHabit(String id) {

    }

    private List<Habit> getSortedHabits() {
        return new ArrayList<Habit>();
    }

    private void notifyChange() {
    }

}
