package main.habitivity.habits;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Habit {
    private String id;
    private String title;
    private Date startDate;
    private List<Integer> daysOfTheWeekToComplete = new ArrayList<>();
    private List<HabitEvent> completions = new ArrayList<>();
    private String habitType;

    public Habit() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
    }

    public String getHabitType() {
        return habitType;
    }

    public void setHabitType(String habitType) {

    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
    }

    public List<Integer> getDaysOfTheWeekToComplete() {
        return daysOfTheWeekToComplete;
    }

    public void dayCompletedOn(Date date) {
    }

    public void setDaysOfTheWeekToComplete(List<Integer> daysToComplete) {
    }
    
    public List<HabitEvent> getCompletedEvents() {
        return completions;
    }

    public void setCompletedEvents(List<? extends HabitEvent> completions) {

    }

    public void addCompletedEvent(HabitEvent habitCompletion){

    }

    public void addHabitEvent(HabitEvent habitEvent){
        
    }

    public void deleteHabitEvent(HabitEvent habitEvent){
        
    }


 
}
