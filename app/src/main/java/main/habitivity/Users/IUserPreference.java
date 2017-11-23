package main.habitivity.Users;

/**
 * Created by Shally on 2017-11-22.
 */
public interface IUserPreference {

    /**
     * Gets the username of the local user
     * @return
     */
    String getUserName();


    /**
     * Gets the local user
     *
     * @return the local user
     */
    User getUser();

    /**
     * Saves the local user
     * @param user
     */
    void saveUser(User user);
}