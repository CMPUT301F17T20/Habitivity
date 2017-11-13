package main.habitivity.interactions;

import java.util.List;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.IHabitRepository;

public class GetHabitEvents {
    private IHabitRepository habitRepository;

    public GetHabitEvents(IHabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public List<HabitEvent> getListOfHabitEvents() {
        return habitRepository.getHabitEvents();
    }
}
