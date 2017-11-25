package main.habitivity.users;

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

public class User implements Serializable, Parcelable{
    private String username;
    private ArrayList<Habit> habits;
    private ArrayList<User> followers;
    private ArrayList<User> following;

    @JestId
    private String uid;

    public User(String username, ArrayList<Habit> habits, ArrayList<User> followers, ArrayList<User> following){
        this.username = username;
        this.habits = habits;
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

    public void setHabits(ArrayList<Habit> habits){
        this.habits = habits;
    }

    public void addHabit(Habit habit){
        this.habits.add(habit);
    }
    public ArrayList<Habit> getHabits(){
        return this.habits;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}