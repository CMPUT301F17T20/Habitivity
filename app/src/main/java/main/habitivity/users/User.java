package main.habitivity.users;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Predicate;

import io.searchbox.annotations.JestId;
import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;

public class User implements Serializable, Parcelable{
    private String username;
    private ArrayList<Habit> habits;
    private ArrayList<String> followers;
    private ArrayList<HabitEvent> habitEvents;
    private ArrayList<String> following;
    private ArrayList<String> followerRequests;
    private ArrayList<String> potentialFriends;
    private ArrayList<String> pendingRequest;
    private Date lastLogin;

    @JestId
    private String uid;

    public User(String username, ArrayList<Habit> habits, ArrayList<HabitEvent> habitEvents, ArrayList<String> followers, ArrayList<String> following){
        this.username = username;
        this.habits = habits;
        this.habitEvents = habitEvents;
        this.followers = followers;
        this.following = following;
        this.followerRequests = new ArrayList<String>();
        this.potentialFriends = new ArrayList<String>();
        this.pendingRequest = new ArrayList<String>();
        this.lastLogin = new Date();
    }

    protected User(Parcel in) {
        username = in.readString();
        followers = in.createStringArrayList();
        following = in.createStringArrayList();
        followerRequests = in.createStringArrayList();
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

    public void updateHabit(Habit oldHabit, Habit newHabit){
        if(this.habits.contains(oldHabit)){
            int index = this.habits.indexOf(oldHabit);
            this.habits.set(index, newHabit);
        }
    }
    public void updateHabitEvent(HabitEvent oldHabitEvent, HabitEvent newHabitEvent){
        if(this.habitEvents.contains(oldHabitEvent)){
            int index = this.habitEvents.indexOf(oldHabitEvent);
            this.habitEvents.set(index, newHabitEvent);
        }
    }

    /**
     * Gets the habits of the user
     * @return - The list of habits of the user
     */
    public ArrayList<Habit> getHabits(){
        return this.habits;
    }

    public void findAllPotentialFriends(){
        ArrayList<User> allUsersExcludingUser = UserContainer.getInstance().getAllUsersExcludingUser();

        for(User potentialFriend: allUsersExcludingUser){
            if(!this.potentialFriends.contains(potentialFriend.getUserName()) && !this.pendingRequest.contains(potentialFriend.getUserName())
                    && !this.following.contains(potentialFriend.getUserName())){
                this.potentialFriends.add(potentialFriend.getUserName());
            }
        }

    }

    public ArrayList<String> getPotentialFriends(){
        this.findAllPotentialFriends();
        return this.potentialFriends;
    }

    /**
     * Removes the habit event from the user's habit event list
     * @param  - id of the habitEvent to remove
     */
    public void removeHabitEvent(final HabitEvent habitEventToRemove){
        habitEvents.removeIf(new Predicate<HabitEvent>() {
            @Override
            public boolean test(HabitEvent habitEvent) {
                return (habitEvent.getId().equals(habitEventToRemove.getId()) && habitEvent.getCompletionDate().equals(habitEventToRemove.getCompletionDate()));
            }
        });
    }

    /**
     * Adds the specified user to the user's follower's list
     * @param followerToAdd - the user to add to the follower's list
     */
    public void addFollower(String followerToAdd){

        if(this.followers.size() == 0){
            this.followers.add(followerToAdd);
            return;
        }

        for(String userInFollowerList: this.followers){
            if(!userInFollowerList.equals(followerToAdd)){
                this.followers.add(followerToAdd);
            }
        }
    }

    /**
     * Remove the specified user from the user's follower's list
     * @param followerToRemove - the user to remove from the follower's list
     */
    public void deleteFollower(String followerToRemove){
        this.followers.remove(followerToRemove);

    }

    public void addToPendingRequest(String user){
        this.pendingRequest.add(user);
    }

    public ArrayList<String> getPendingRequests(){
        return this.pendingRequest;
    }
    /**
     * Gets the list of followers for the user
     * @return - the list of followers of the user
     */
    public ArrayList<String> getFollowers(){
        return this.followers;
    }

    public void removePotentialFriend(int pos){
        this.potentialFriends.remove(pos);
    }

    public ArrayList<String> getFollowerRequests(){
        return this.followerRequests;
    }

    public void addFollowerRequest(String follower){
        if(this.followerRequests.size() == 0){
            this.followerRequests.add(follower);
            return;
        }
        for(String followerInList: this.followerRequests){
            //the follower is already in the request list
            if(followerInList.equals(follower)){
                return;
            }
        }
        this.followerRequests.add(follower);
    }

    public void removeFollowerRequest(int pos){
        this.followerRequests.remove(pos);
    }

    public ArrayList<String> getFollowersAsString(){
        return this.followers;
    }

    /**
     * Adds the user to the list of users that we're following
     * @param user - the user to add to our following list
     */
    public void addFollowing(String user){
        if(this.following.size() == 0){
            this.following.add(user);
            return;
        }

        for(String userInFollowerList: this.following){
            if(!userInFollowerList.equals(user)){
                this.following.add(user);
                return;
            }
        }
    }

    /**
     * Removes the specified user from the list of users that we're following
     * @param user - the user to remove from our following list
     */
    public void deleteFollowing(String user){
        this.following.remove(user);

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
     * @param  - id of habit to remove
     */
    public void removeHabit(final Habit habitToRemove){
        habits.removeIf(new Predicate<Habit>() {
            @Override
            public boolean test(Habit habit) {
                return habit.getId().equals(habitToRemove.getId());
            }
        });
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

    public void updateOfflineDays(){
        Date today = new Date();
        ArrayList<Date> skipDay = new ArrayList<>();

        for(Habit habit: this.habits ){
            for(HabitEvent event: this.habitEvents ){
                if (event.getId().equals(habit.getId()) && event.getOnSched()){
                    skipDay.add(event.getCompletionDate());
                }
            }
            habit.addPassedDayCount(this.lastLogin, today, true, skipDay);
        }
        this.lastLogin = today;
    }

    public Date getLastLogin(){ return this.lastLogin; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(username);
        parcel.writeStringList(followers);
        parcel.writeStringList(following);
        parcel.writeStringList(followerRequests);
        parcel.writeString(uid);
    }
}