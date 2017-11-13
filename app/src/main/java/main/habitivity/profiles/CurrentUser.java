package main.habitivity.profiles;
/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */

/**
 * A Singleton Class to get our current user
 */
public class CurrentUser {
    private static final CurrentUser ourInstance = new CurrentUser();

    private String currentUserId;

    /**
     * Set the name of our current user
     * @param userId to set for our current user
     */
    public void setCurrentUser(String userId){
        this.currentUserId = userId;
    }

    /**
     * Gets our current user
     * @return the name of our current user
     */
    public String getCurrentUser(){
        return this.currentUserId;
    }

    public static CurrentUser getInstance() {
        return ourInstance;
    }

    private CurrentUser() {
    }
}
