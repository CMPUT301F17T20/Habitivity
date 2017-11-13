package main.habitivity.interactions;

import main.habitivity.habits.HabitRepository;
import main.habitivity.habits.IHabitRepository;

public class HabitInteractionsFactory {
    private IClock clock;
    private IHabitRepository habitRepository;

    public HabitInteractionsFactory(IHabitRepository habitRepository, IClock clock) {
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

    public UpdateHabit updateHabit(){
        return new UpdateHabit(habitRepository);
    }

    public UpdateHabitEvent updateHabitEvent(){
        return new UpdateHabitEvent(habitRepository);
    }

    public GetHabits getHabits(){
        return new GetHabits(habitRepository);
    }

    public GetHabitEvents getHabitEvents(){
        return new GetHabitEvents(habitRepository);
    }
    public DeleteHabit deleteHabit() {
        return new DeleteHabit(habitRepository);
    }
}
