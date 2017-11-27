/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */

package main.habitivity.controllers;


import com.google.common.collect.ArrayTable;

import java.util.ArrayList;
import java.util.List;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.interactions.HabitInteractionsFactory;
import main.habitivity.users.User;
import main.habitivity.users.UserContainer;

/**
 * Controls getting the habits and habitevents stored in our repo. Also controls removing
 * habitEvents and habits. Will move the remove functions to a remove controller later.
 */
public class HabitListController {
    private HabitInteractionsFactory habitInteractionsFactory;
    private ArrayList<User> allUsers;


    public HabitListController(HabitInteractionsFactory habitInteractionsFactory) {
        this.habitInteractionsFactory = habitInteractionsFactory;
    }

    public ArrayList<Habit> getAllHabitsOfUserAndFollowing(){
        ArrayList<Habit> usersAndFollowingHabits = new ArrayList<Habit>();
        allUsers = AllUsersController.getAllUsers();
        User currentlyLoggedInUser = UserContainer.getInstance().getUser();
        for(User user: allUsers) {
            for(User following: currentlyLoggedInUser.getFollowing()){
                if(following.getUserName().equals(user.getUserName())){
                    usersAndFollowingHabits.addAll(user.getHabits());
                    continue;
                }
            }
        }
        return usersAndFollowingHabits;
    }
    /**
     * Get a list of habits stored in our repo
     * @return list of habits
     */
    public List<Habit> getHabits() {
        return habitInteractionsFactory.getHabits().getListOfHabits();
    }

    /**
     * Remove the habit from our habit repo
     * @param habit to remove
     */
    public void removeHabit(Habit habit){
        habitInteractionsFactory.deleteHabit().delete(habit.getId());
    }

    /**
     * Remove the habitEvent from our habit repo
     * @param habitEvent to remove
     */
    public void removeHabitEvent(HabitEvent habitEvent){
        habitInteractionsFactory.removeHabitEvent().remove(habitEvent.getId());
    }

    /**
     * Gets the list of HabitEvents in our repo
     * @return a list of habit events
     */
    public List<HabitEvent> getHabitEvents() {
        return habitInteractionsFactory.getHabitEvents().getListOfHabitEvents();
    }

}