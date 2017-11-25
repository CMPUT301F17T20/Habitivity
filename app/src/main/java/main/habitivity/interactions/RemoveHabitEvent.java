/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */
package main.habitivity.interactions;

import main.habitivity.controllers.ElasticsearchController;
import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.HabitRepository;
import main.habitivity.habits.IHabitRepository;
import main.habitivity.users.User;
import main.habitivity.users.UserContainer;

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

        User currentlylogged = UserContainer.getInstance().getUser();
        currentlylogged.removeHabitEvent(habitEventId);
        ElasticsearchController.UpdateUserTask updateUserTask = new ElasticsearchController.UpdateUserTask();
        updateUserTask.execute(currentlylogged);
    }

}
