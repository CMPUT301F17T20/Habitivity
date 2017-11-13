package main.habitivity.interactions;

import java.util.Date;
import java.util.List;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitRepository;
import main.habitivity.habits.IHabitRepository;

public class AddHabit {
    private IHabitRepository habitRepository;

    public AddHabit(IHabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public void add(String id, Date startDate, List<Integer> days) {
        Habit habit = new Habit();
        habit.setId(id);
        habit.setStartDate(startDate);
        habit.setDaysOfTheWeekToComplete(days);

        habitRepository.addHabit(habit);
    }
}
