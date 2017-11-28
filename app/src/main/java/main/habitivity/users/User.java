package main.habitivity.users;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import io.searchbox.annotations.JestId;
import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;

public class User implements Serializable, Parcelable{
    private String username;
    private ArrayList<Habit> habits;
    private ArrayList<String> followers;
    private ArrayList<HabitEvent> habitEvents;
    private ArrayList<String> following;

    @JestId
    private String uid;

    public User(String username, ArrayList<Habit> habits, ArrayList<HabitEvent> habitEvents, ArrayList<String> followers, ArrayList<String> following){
        this.username = username;
        this.habits = habits;
        this.habitEvents = habitEvents;
        this.followers = followers;
        this.following = following;
    }

    /**
     * Set the userID of the User
     * @param uid UserID string
     */
    public void setId(String uid){
        this.uid = uid;
    }

    /**
     * Get the userID of the User
     * @return UserID string
     */
    public String getId(){
        return uid;
    }

    /**
     * Set the username of the User
     * @param username Username string
     */
    public void setUserName(String username){
        this.username = username;
    }

    /**
     * Get the username of the User
     * @return Username string
     */
    public String getUserName(){
        return username;
    }

    /**
     * Set the habits of the user
     * @param habits - habits to set
     */
    public void setHabits(ArrayList<Habit> habits){
        this.habits = habits;
    }

    /**
     * Adds the habit to the user's habits list
     * @param habit - habit to add
     */
    public void addHabit(Habit habit){
        this.habits.add(habit);
    }

    /**
     * Gets the habits of the user
     * @return - The list of habits of the user
     */
    public ArrayList<Habit> getHabits(){
        return this.habits;
    }

    /**
     * Removes the habit event from the user's habit event list
     * @param id - id of the habitEvent to remove
     */
    public void removeHabitEvent(String id){
        for(HabitEvent habitEvent: this.habitEvents){
            if(habitEvent.getId() == id){
                this.habitEvents.remove(habitEvent);
            }
        }
    }

    /**
     * Adds the specified user to the user's follower's list
     * @param followerToAdd - the user to add to the follower's list
     */
    public void addFollower(User followerToAdd){

        if(this.followers.size() == 0){
            this.followers.add(followerToAdd.getUserName());
            return;
        }

        for(String userInFollowerList: this.followers){
            if(!userInFollowerList.equals(followerToAdd.getUserName())){
                this.followers.add(followerToAdd.getUserName());
            }
        }
    }

    /**
     * Remove the specified user from the user's follower's list
     * @param followerToRemove - the user to remove from the follower's list
     */
    public void deleteFollower(User followerToRemove){
        this.followers.remove(followerToRemove.getUserName());

    }

    /**
     * Gets the list of followers for the user
     * @return - the list of followers of the user
     */
    public ArrayList<User> getFollowers(){
        ArrayList<User> allUsers = UserContainer.getInstance().getAllUsers();
        Boolean userInFollowers = false;
        for(User user: allUsers){
            for(String followers: this.followers){
                if(user.getUserName().equals(followers)){
                    userInFollowers = true;
                    break;
                }
            }
            if(userInFollowers == false){
                allUsers.remove(user);
            }
            else{
                userInFollowers = true;
            }
        }
        return allUsers;
    }

    /**
     * Adds the user to the list of users that we're following
     * @param user - the user to add to our following list
     */
    public void addFollowing(User user){
        if(this.following.size() == 0){
            this.following.add(user.getUserName());
            return;
        }

        for(String userInFollowerList: this.following){
            if(!userInFollowerList.equals(user.getUserName())){
                this.following.add(user.getUserName());
            }
        }
    }

    /**
     * Removes the specified user from the list of users that we're following
     * @param user - the user to remove from our following list
     */
    public void deleteFollowing(User user){
        this.following.remove(user.getUserName());

    }

    /**
     * Get the list of users that the User is following
     * @return - the list of users that the user is following
     */
    public ArrayList<User> getFollowing(){
        ArrayList<User> allUsers = UserContainer.getInstance().getAllUsers();
        ArrayList<User> listToReturn = new ArrayList(allUsers);
        Integer userInFollowers = 0;
        for(User user: allUsers){
            for(String following: this.following){
                if(user.getUserName().equals(following)){
                    userInFollowers = 1;
                    break;
                }
            }
            if(userInFollowers == 0){
                listToReturn.remove(user);
            }
            else{
                userInFollowers = 1;
            }
        }
        return listToReturn;
    }

    /**
     * Removes the habit from the user's habit's list
     * @param id - id of habit to remove
     */
    public void removeHabit(String id){
        for(Habit habit: this.habits){
            if(habit.getId() == id){
                this.habits.remove(habit);
            }
        }
    }

    /**
     * Sets the habitEvents of the user
     * @param habitEvents - the habitEvents to set for the user
     */
    public void setHabitEvents(ArrayList<HabitEvent> habitEvents){
        this.habitEvents = habitEvents;
    }

    /**
     * Adds the habitEvent to the user's habitEvent list
     * @param habitEvent - habitEvent to add
     */
    public void addHabitEvent(HabitEvent habitEvent){
        this.habitEvents.add(habitEvent);
    }

    /**
     * Gets the list of habitEvents of the user
     * @return
     */
    public ArrayList<HabitEvent> getHabitEvents() {
        return this.habitEvents;
    }

    @Override
    /**
     * ArrayAdapter calls toString() on the object and displays it on the TextView. Override to display what we want
     */
    public String toString(){
        return "User: " + this.getUserName();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}