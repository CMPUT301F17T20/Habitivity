package main.habitivity.interactions;

import java.util.Date;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.HabitRepository;
import main.habitivity.habits.IHabitRepository;

public class AddHabitEvent {
    private IClock clock;
    private IHabitRepository habitRepository;

    public AddHabitEvent(IHabitRepository habitRepository, IClock clock) {
        this.habitRepository = habitRepository;
        this.clock = clock;
    }

    public void complete(String id) {
        Habit habit = habitRepository.getHabit(id);

        if (habit != null) {
            habitRepository.updateHabit(this.getHabitUpdatedWithEvent(habit));
        }
    }

    public void add(String id, String comment){
        HabitEvent habitEvent = generateHabitEvent();
        habitEvent.setComment(comment);
        habitEvent.setId(id);

        habitRepository.addHabitEvent(habitEvent);
    }

    private Habit getHabitUpdatedWithEvent(Habit habit) {
        habit.getCompletedEvents().add(generateHabitEvent());
        return habit;
    }

    private HabitEvent generateHabitEvent() {
        return new HabitEvent(clock.getCurrentDate());
    }
}
