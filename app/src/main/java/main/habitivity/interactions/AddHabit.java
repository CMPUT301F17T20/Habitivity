package main.habitivity.interactions;

import java.util.Date;
import java.util.List;

import main.habitivity.habits.HabitRepository;

public class AddHabit {
    private HabitRepository habitRepository;

    public AddHabit(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public void add(String name, Date startDate, List<Integer> days) {

    }
}
