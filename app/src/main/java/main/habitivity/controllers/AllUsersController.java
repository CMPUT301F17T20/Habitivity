package main.habitivity.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import main.habitivity.habits.Habit;
import main.habitivity.interactions.HabitInteractionsFactory;
import main.habitivity.users.User;
import main.habitivity.users.UserContainer;

/**
 * Created by Shally on 2017-11-26.
 */

public class AllUsersController {

    public static ArrayList<User> getAllUsers() {
        ArrayList<User> allUsers = new ArrayList();

        ElasticsearchController.GetAllUserTask getAllUsers
                = new ElasticsearchController.GetAllUserTask();
        try {
            getAllUsers.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            allUsers = getAllUsers.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return allUsers;
    }
    public static ArrayList<User> getAllUsersExcludingCurrentUser() {
        ArrayList<User> allUsers = new ArrayList();

        ElasticsearchController.GetAllUserTask getAllUsers
                = new ElasticsearchController.GetAllUserTask();
        try {
            getAllUsers.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            allUsers = getAllUsers.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        String currentUserName =  UserContainer.getInstance().getUser().getUserName();
        for(User user: allUsers){
            if(user.getUserName().equals(currentUserName)){
                allUsers.remove(user);
                break;
            }
        }
        return allUsers;
    }

}
