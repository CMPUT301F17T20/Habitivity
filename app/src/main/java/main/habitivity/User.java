package main.habitivity;


import main.habitivity.Users.Identifiable;

public class User implements Identifiable {
    private String userName;

    public User() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
