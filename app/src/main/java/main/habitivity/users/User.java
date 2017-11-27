package main.habitivity.users;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

import io.searchbox.annotations.JestId;
import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;

public class User implements Serializable, Parcelable{
    private String username;
    private ArrayList<Habit> habits;
    private ArrayList<User> followers;
    private ArrayList<HabitEvent> habitEvents;
    private ArrayList<User> following;

    @JestId
    private String uid;

    public User(String username, ArrayList<Habit> habits, ArrayList<HabitEvent> habitEvents, ArrayList<User> followers, ArrayList<User> following){
        this.username = username;
        this.habits = habits;
        this.habitEvents = habitEvents;
        this.followers = followers;
        this.following = following;
    }

    protected User(Parcel in) {
        username = in.readString();
        followers = in.createTypedArrayList(User.CREATOR);
        following = in.createTypedArrayList(User.CREATOR);
        uid = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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
        this.followers.add(followerToAdd);
    }

    /**
     * Remove the specified user from the user's follower's list
     * @param followerToRemove - the user to remove from the follower's list
     */
    public void deleteFollower(User followerToRemove){
        this.followers.remove(followerToRemove);
    }

    /**
     * Gets the list of followers for the user
     * @return - the list of followers of the user
     */
    public ArrayList<User> getFollowers(){
        return this.followers;
    }

    /**
     * Adds the user to the list of users that we're following
     * @param user - the user to add to our following list
     */
    public void addFollowing(User user){
        this.following.add(user);
    }

    /**
     * Removes the specified user from the list of users that we're following
     * @param user - the user to remove from our following list
     */
    public void deleteFollowing(User user){
        this.following.remove(user);
    }

    /**
     * Get the list of users that the User is following
     * @return - the list of users that the user is following
     */
    public ArrayList<User> getFollowing(){
        return this.following;
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