package main.habitivity.interactions;

import java.util.Date;

public class Clock implements IClock {
    @Override
    public Date getCurrentDate() {
        return new Date();
    }
}
