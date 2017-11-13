package main.habitivity.interactions;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.HabitRepository;
import main.habitivity.habits.IHabitRepository;

/**
 * Interaction class to remove habit events
 */
public class RemoveHabitEvent {
    private IHabitRepository habitRepository;

    public RemoveHabitEvent(IHabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    /**
     * remove the habitEvent from our habit repo
     * @param habitEventId of habitEvent to remove
     */
    public void remove(String habitEventId){
        habitRepository.removeHabitEvent(habitEventId);
    }

}
