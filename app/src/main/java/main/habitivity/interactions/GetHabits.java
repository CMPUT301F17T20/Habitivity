package main.habitivity.interactions;

import java.util.Date;
import java.util.List;

import main.habitivity.habits.Habit;
import main.habitivity.habits.IHabitRepository;


/**
 * Interaction class to get habits in our habit repo
 */
public class GetHabits {
    private IHabitRepository habitRepository;

    public GetHabits(IHabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    /**
     * Gets a list of habits from our habit repo
     * @return a list of habits from our habit repo
     */
    public List<Habit> getListOfHabits() {
        return habitRepository.getHabits();
    }
}
