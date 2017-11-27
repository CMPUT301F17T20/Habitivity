/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */
package main.habitivity.interactions;


import android.graphics.Bitmap;
import android.location.Location;

import java.util.Date;
import java.util.List;

import main.habitivity.controllers.ElasticsearchController;
import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.IHabitRepository;
import main.habitivity.users.User;
import main.habitivity.users.UserContainer;

/**
 * Interaction class to update habit events
 *
 * */
public class UpdateHabitEvent {
    private IHabitRepository habitRepository;
    private User currentlyLoggedInUser;

    public UpdateHabitEvent(IHabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    /**
     * Update the habit event
     * @param id of habit to update
     * @param startDate id of habit to update
     * @param comment id of habit to update
     */
    public void update(String id, Date startDate, String comment, Bitmap photo, Location location) {
        HabitEvent habitEvent = new HabitEvent(new Date());
        habitEvent.setId(id);
        habitEvent.setComment(comment);
        habitEvent.setCompletionDate(startDate);
        habitEvent.setLocation(location);
        habitEvent.setPhoto(photo);

        currentlyLoggedInUser = UserContainer.getInstance().getUser();
        habitRepository.updateHabitEvent(habitEvent);
    }
}
