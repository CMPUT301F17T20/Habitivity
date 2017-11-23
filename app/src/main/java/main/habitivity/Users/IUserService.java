package main.habitivity.Users;

/**
 * Created by Shally on 2017-11-22.
 */
public interface IUserService {


    User getUser(String username);


    void createUser(User user);


    User getCurrentUser();


    void setCurrentUser(User user);


    void updateUser(User user);


    void updateCurrentUser(User user);
}