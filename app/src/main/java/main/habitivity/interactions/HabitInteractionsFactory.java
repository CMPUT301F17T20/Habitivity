package main.habitivity.interactions;

import main.habitivity.habits.HabitRepository;

public class HabitInteractionsFactory {
    private IClock clock;
    private HabitRepository habitRepository;

    public HabitInteractionsFactory(HabitRepository habitRepository, IClock clock) {
        this.habitRepository = habitRepository;
        this.clock = clock;
    }

    public RemoveHabitEvent removeHabitEvent() {
        return new RemoveHabitEvent(habitRepository);
    }

    public AddHabitEvent addHabitEvent() {
        return new AddHabitEvent(habitRepository, clock);
    }

    public AddHabit addHabit() {
        return new AddHabit(habitRepository);
    }

    public DeleteHabit deleteHabit() {
        return new DeleteHabit(habitRepository);
    }
}
