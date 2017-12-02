/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */
package main.habitivity.interactions;

import java.util.Date;
import java.util.List;

import main.habitivity.controllers.ElasticsearchController;
import main.habitivity.habits.Habit;
import main.habitivity.habits.IHabitRepository;
import main.habitivity.users.User;
import main.habitivity.users.UserContainer;

/**
 * Interaction class to control updating the Habits
 */
public class UpdateHabit {
    private IHabitRepository habitRepository;
    private User currentlyLoggedInUser;

    public UpdateHabit(IHabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    /**
     * Updates the current habit
     * @param id of habit to update
     * @param startDate of habit to update
     * @param reason of habit to update
     * @param days of habit to update
     */
    public void update(String id, Habit oldHabit, Date startDate, String reason, List<Integer> days, String habitType) {
        Habit habit = new Habit();
        currentlyLoggedInUser = UserContainer.getInstance().getUser();
        habit.setUserName(currentlyLoggedInUser.getUserName());
        habit.setId(id);
        habit.setTitle(id);
        habit.setStartDate(startDate);
        habit.setReason(reason);
        habit.setDaysOfTheWeekToComplete(days);
        habit.setHabitType(habitType);


        currentlyLoggedInUser.updateHabit(oldHabit, habit);
        habitRepository.updateHabit(oldHabit, habit);

        //Update on the server
//        ElasticsearchController.UpdateUserTask updateUserTask = new ElasticsearchController.UpdateUserTask();
//        updateUserTask.execute(currentlyLoggedInUser);
    }
}
