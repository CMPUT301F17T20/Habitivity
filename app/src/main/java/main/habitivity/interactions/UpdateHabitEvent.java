package main.habitivity.interactions;


import java.util.Date;
import java.util.List;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.IHabitRepository;

/**
 * Interaction class to update habit events
 *
 * */
public class UpdateHabitEvent {
    private IHabitRepository habitRepository;

    public UpdateHabitEvent(IHabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    /**
     * Update the habit event
     * @param id of habit to update
     * @param startDate id of habit to update
     * @param comment id of habit to update
     */
    public void update(String id, Date startDate, String comment) {
        HabitEvent habitEvent = new HabitEvent(new Date());
        habitEvent.setId(id);
        habitEvent.setComment(comment);
        habitEvent.setCompletionDate(startDate);

        habitRepository.updateHabitEvent(habitEvent);
    }
}
