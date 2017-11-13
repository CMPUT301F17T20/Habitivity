package main.habitivity.interactions;

import java.util.Date;
import java.util.List;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitRepository;
import main.habitivity.habits.IHabitRepository;

/**
 * Interaction class that helps add habits to our hait repo
 */
public class AddHabit {
    private IHabitRepository habitRepository;

    public AddHabit(IHabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    /**
     * Adds a habit to our habit repo
     * @param id of habit
     * @param startDate of habit
     * @param days of habit
     */
    public void add(String id, Date startDate, List<Integer> days) {
        Habit habit = new Habit();
        habit.setId(id);
        habit.setTitle(id);
        habit.setStartDate(startDate);
        habit.setDaysOfTheWeekToComplete(days);

        habitRepository.addHabit(habit);
    }
}
