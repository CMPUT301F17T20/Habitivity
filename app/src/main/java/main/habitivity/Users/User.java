package main.habitivity.Users;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

import io.searchbox.annotations.JestId;
import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitRepository;

/**
 * Created by Shally on 2017-11-22.
 */

public class User implements Serializable, Parcelable, Identifiable {
    private String username;
    private ArrayList<Habit> habits;
    private ArrayList<User> friends;

    @JestId
    private String uid;

    public User(String username, ArrayList<Habit> habits, ArrayList<User> friends){
        this.username = username;
        this.habits = habits;
        this.friends = friends;
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
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * Get the username of the User
     * @return Username string
     */
    public String getUsername(){
        return username;
    }

    public void setHabits(ArrayList<Habit> habits){
        this.habits = habits;
    }

    public void addHabit(Habit habit){
        this.habits.add(habit);
    }
    public ArrayList<Habit> getHabits(){
        return this.habits;
    }

    /**
     * Get the friends of the User
     * @return
     */
    public ArrayList<User> getFriends(){
        return this.friends;
    }

    /**
     * Add a friend to the User's friend-list
     * @param friend
     */
    public void addFriend(User friend){
        this.friends.add(friend);
    }

    /**
     * Checks is the user has the friend
     * @param friend Friend to check
     * @return True is already in our friend list, false otherwise
     */
    public boolean hasFriend(User friend){
        return friends.contains(friend);
    }

    /**
     * Delete friend from our friend list
     * @param friend - User to remove
     */
    public void deleteFriend(User friend){
        friends.remove(friend);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
