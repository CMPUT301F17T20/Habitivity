package main.habitivity.interactions;

import main.habitivity.habits.HabitRepository;
import main.habitivity.habits.IHabitRepository;

/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */

/**
 * Interaction Class Factory. Controls all the interaction between the user and the controllers
 */
public class HabitInteractionsFactory {
    private IClock clock;
    private HabitRepository habitRepository;

    public HabitInteractionsFactory(HabitRepository habitRepository, IClock clock) {
        this.habitRepository = habitRepository;
        this.clock = clock;
    }

    public void setHabitRepository(HabitRepository habitRepo){
        this.habitRepository = habitRepo;
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

    public SetHabits setHabits() {return new SetHabits(habitRepository);}

    public GetHabitEvents getHabitEvents(){
        return new GetHabitEvents(habitRepository);
    }
    public DeleteHabit deleteHabit() {
        return new DeleteHabit(habitRepository);
    }
}