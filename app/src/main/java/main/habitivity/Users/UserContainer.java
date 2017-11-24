package main.habitivity.Users;

/**
 * Created by Shally on 2017-11-23.
 */

public class UserContainer {
    private static final UserContainer ourInstance = new UserContainer();
    private User user;
    public static UserContainer getInstance() {
        return ourInstance;
    }

    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){
        return this.user;
    }

    private UserContainer() {
    }
}
