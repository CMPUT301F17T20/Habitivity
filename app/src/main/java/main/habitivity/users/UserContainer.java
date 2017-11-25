package main.habitivity.users;

import android.util.Log;

import java.util.concurrent.ExecutionException;

import main.habitivity.controllers.ElasticsearchController;

/**
 * Singleton class which helps us determine which user is currently logged in
 */
public class UserContainer {
    private static final UserContainer ourInstance = new UserContainer();
    private User user;
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
     * Gets the Current User
     * @return
     */
    public User getUser(){

        return this.user;
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