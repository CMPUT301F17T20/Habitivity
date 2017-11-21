/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */

package main.habitivity.interactions;

import java.util.Date;
import java.util.List;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitRepository;
import main.habitivity.habits.IHabitRepository;

/**
 * Interaction class that helps add habits to our hait repo
 */
public class AddHabit {
    private IHabitRepository habitRepository;

    public AddHabit(IHabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    /**
     * Adds a habit to our habit repo
     * @param id of habit
     * @param startDate of habit
     * @param days of habit
     */
    public void add(String id, Date startDate, List<Integer> days, String habitType) {
        Habit habit = new Habit();
        habit.setHabitType(habitType);
        //need a way to unqiuely identify a habit. TODO i have an idea but implment later
        habit.setId(id);
        habit.setTitle(id);
        habit.setStartDate(startDate);
        habit.setDaysOfTheWeekToComplete(days);

        habitRepository.addHabit(habit);
    }
}
