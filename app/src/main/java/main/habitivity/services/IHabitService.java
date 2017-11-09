package main.habitivity.services;

import java.util.List;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;

/**
 * Created by Shally on 2017-11-09.
 */

public interface IHabitService {
    List<Habit> getHabit();

    List<Habit> getHabits();

    void addHabit(Habit habit);
    void deleteHabit(String id);
    void updateHabit(Habit habit);

    List<HabitEvent> getHabitEvent();

    void addHabitEvent(HabitEvent habitEvent);
    void deleteHabitEvent(String id);
    void updateHabitEvent(HabitEvent habitEvent);
}
