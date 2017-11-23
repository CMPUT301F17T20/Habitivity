package main.habitivity.Users;

/**
 * Created by Shally on 2017-11-22.
 */

public class UserController {
    private IUserService userService;

    /**
     * Instantiates a new User controller.
     *
     * @param userService the user service
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Create user.
     *
     * @param user the user
     */
    public void createUser(User user) {
        userService.createUser(user);
    }

    /**
     * Gets user.
     *
     * @param username the username
     * @return the user
     */
    public User getUser(String username) {
        return userService.getUser(username);
    }

    /**
     * Gets current user.
     *
     * @return the current user
     */
    public User getCurrentUser(){
        return userService.getCurrentUser();
    }

    /**
     * Sets current user.
     *
     * @param user the user
     */
    public void setCurrentUser(User user) { userService.setCurrentUser(user); }

    /**
     * Update user.
     *
     * @param user the user
     */
    public void updateUser(User user) {
        userService.updateUser(user);
    }

    /**
     * Update current user.
     *
     * @param user the user
     */
    public void updateCurrentUser(User user) { userService.updateCurrentUser(user); }

}