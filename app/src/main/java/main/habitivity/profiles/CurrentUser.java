package main.habitivity.profiles;


/**
 * A Singleton Class to get our current user
 */
public class CurrentUser {
    private static final CurrentUser ourInstance = new CurrentUser();

    private String currentUserId;

    /**
     * Set the name of our current user
     * @param userId to set for our current user
     */
    public void setCurrentUser(String userId){
        this.currentUserId = userId;
    }

    /**
     * Gets our current user
     * @return the name of our current user
     */
    public String getCurrentUser(){
        return this.currentUserId;
    }

    public static CurrentUser getInstance() {
        return ourInstance;
    }

    private CurrentUser() {
    }
}
