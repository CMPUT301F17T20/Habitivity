package main.habitivity.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import main.habitivity.habits.Habit;
import main.habitivity.interactions.HabitInteractionsFactory;
import main.habitivity.users.User;
import main.habitivity.users.UserContainer;

/**
 * Controls grabbing users from the server
 */
public class AllUsersController {

    public AllUsersController() {
    }

    /**
     * Gets a list of users that the currently logged in user is following
     *
     * @return - a list of users that the user is currently following
     */
    public ArrayList<String> getUsersFollowers() {
        return UserContainer.getInstance().getUser().getFollowers();
    }

    /**
     * Gets a list of users that the currently logged in user is following
     *
     * @return - a list of users that the user is currently following
     */
    public ArrayList<User> getUsersFollowing() {
        User currentLoggedInUser = UserContainer.getInstance().getUser();
        ArrayList<User> userFollowing = new ArrayList<User>();

        for (User following : currentLoggedInUser.getFollowing()) {
            userFollowing.add(following);
        }
        return userFollowing;
    }

    /**
     * Gets all the users in the server
     *
     * @return - a list of all the users in the server
     */
    public static ArrayList<User> getAllUsers() {
        ArrayList<User> allUsers = new ArrayList<>();

        ElasticsearchController.GetAllUserTask getAllUsers
                = new ElasticsearchController.GetAllUserTask();

        getAllUsers.execute();

        try {
            allUsers = getAllUsers.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        UserContainer.getInstance().setAllUsers(allUsers);
        return allUsers;
    }
}
