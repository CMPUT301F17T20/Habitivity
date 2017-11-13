package main.habitivity.controllers;

import java.util.Date;
import java.util.List;

/**
 * An object that encapsulates all the components required to
 * create a new habit
 */
public class AddHabitRequest {
    private String name;
    private String reason;
    private Date startDate;
    private List<Integer> daysOfTheWeek;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<Integer> getDaysOfTheWeek() {
        return daysOfTheWeek;
    }

    public void setDaysOfTheWeek(List<Integer> daysOfTheWeek) {
        this.daysOfTheWeek = daysOfTheWeek;
    }
}