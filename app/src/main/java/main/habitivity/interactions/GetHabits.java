package main.habitivity.interactions;

import java.util.Date;
import java.util.List;

import main.habitivity.habits.Habit;
import main.habitivity.habits.IHabitRepository;

/**
 * Created by Shally on 2017-11-12.
 */

public class GetHabits {
    private IHabitRepository habitRepository;

    public GetHabits(IHabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public List<Habit> getListOfHabits() {
        return habitRepository.getHabits();
    }
}
