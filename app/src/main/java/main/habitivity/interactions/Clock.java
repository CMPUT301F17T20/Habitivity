/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */

package main.habitivity.interactions;

import java.util.Date;

/**
 * Clock class to get the current time. Helps with testing
 */
public class Clock implements IClock {
    @Override
    /**
     * Gets the current date
     */
    public Date getCurrentDate() {
        return new Date();
    }
}
