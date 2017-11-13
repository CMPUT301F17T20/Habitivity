package main.habitivity.interactions;

import java.util.List;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.IHabitRepository;

/**
 * Interaction class to get a list of habitEvents from our repo
 */
public class GetHabitEvents {
    private IHabitRepository habitRepository;

    public GetHabitEvents(IHabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    /**
     * Gets a list of habitEvents from our repo
     * @return a list of HabitEvents
     */
    public List<HabitEvent> getListOfHabitEvents() {
        return habitRepository.getHabitEvents();
    }
}
