package main.habitivity.interactions;

import java.util.Date;
import java.util.List;

import main.habitivity.habits.Habit;
import main.habitivity.habits.IHabitRepository;

/**
 * Interaction class to control updating the Habits
 */
public class UpdateHabit {
    private IHabitRepository habitRepository;

    public UpdateHabit(IHabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    /**
     * Updates the current habit
     * @param id of habit to update
     * @param startDate of habit to update
     * @param reason of habit to update
     * @param days of habit to update
     */
    public void update(String id, Date startDate, String reason, List<Integer> days) {
        Habit habit = new Habit();
        habit.setId(id);
        habit.setTitle(id);
        habit.setStartDate(startDate);
        habit.setReason(reason);
        habit.setDaysOfTheWeekToComplete(days);

        habitRepository.updateHabit(habit);
    }
}
