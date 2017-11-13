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
