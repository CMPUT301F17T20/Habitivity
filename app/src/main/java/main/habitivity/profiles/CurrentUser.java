package main.habitivity.profiles;

/**
 * Created by Shally on 2017-11-13.
 */

public class CurrentUser {
    private static final CurrentUser ourInstance = new CurrentUser();

    private String currentUserId;

    public void setCurrentUser(String userId){
        this.currentUserId = userId;
    }

    public String getCurrentUser(){
        return this.currentUserId;
    }
    public static CurrentUser getInstance() {
        return ourInstance;
    }

    private CurrentUser() {
    }
}
