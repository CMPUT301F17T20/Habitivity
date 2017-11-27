package main.habitivity.users;

import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import main.habitivity.controllers.ElasticsearchController;

/**
 * Singleton class which helps us determine which user is currently logged in
 */
public class UserContainer {
    private static final UserContainer ourInstance = new UserContainer();
    private User user;
    private User userToView;
    private ArrayList<User> allUsers = new ArrayList<>();
    private ArrayList<User> allUsersExcludingUser = new ArrayList<>();
    public static UserContainer getInstance() {
        return ourInstance;
    }

    /**
     * Set the current User
     * @param user
     */
    public void setUser(User user){
        this.user = user;
    }

    /**
     * Gets the user that's currently logged in
     * @return
     */
    public User getUser(){
        return this.user;
    }

    /**
     * Gets the user that we're viewing. This is used when we click on a user in the find friend tab
     * @return - the user we're viewing
     */
    public User getUserToView(){
        return this.userToView;
    }

    /**
     * Sets the user that we're viewing. This is used when we click on a user in the find friend tab
     * @param user - the user to set as the user we're viewing
     * @return - the user we're viewing
     */
    public void setUserToView(User user){
        this.userToView = user;
    }

    public void setAllUsers(ArrayList<User> allUsers){
        this.allUsers = allUsers;
    }

    public void setAllUsersExcludingUser(ArrayList<User> allUsers){
        this.allUsersExcludingUser = allUsers;
    }

    public ArrayList<User> getAllUsersExcludingUser(){
        return this.allUsersExcludingUser;
    }

    public ArrayList<User> getAllUsers(){
        return this.allUsers;
    }

    private UserContainer() {
    }

    /**
     * Gets the Currently logged in user from the elastic search server
     * @param jestID
     * @return
     */
    public static User getUserObject(String jestID) {
        ElasticsearchController.GetUserTask getUserTask
                = new ElasticsearchController.GetUserTask();
        try {
            getUserTask.execute(jestID).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            return getUserTask.get();
        }
        catch (Exception e) {
            Log.i("Error", "Failed to get the user from the server");
            Log.i("Error", e.toString());
        }
        return null;
    }
}