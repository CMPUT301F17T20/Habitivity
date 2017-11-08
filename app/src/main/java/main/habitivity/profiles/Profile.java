package main.habitivity.profiles;

/**
 * This class is needed for when the user creates an account. A profile is created for the user
 */
public class Profile {
    private String userId;

    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String id){
        this.userId = id;
    }
}
