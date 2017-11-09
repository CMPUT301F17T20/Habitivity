package main.habitivity.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Map;

import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;

/**
 * Created by Shally on 2017-11-09.
 *
 * Provides CRUD (create, read, update, delete) methods for habits and habit events stored locally
 * on the device
 */
public class LocalHabitService implements IHabitService {
    private AndroidFileHandler fileHandler;

    public LocalHabitService(AndroidFileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @Override
    public List<HabitEvent> getHabitEvent(){
        return null;
    }

    @Override
    public void addHabitEvent(HabitEvent habitEvent){

    }

    @Override
    public void deleteHabitEvent(String id){

    }

    public void updateHabitEvent(HabitEvent habitEvent){

    }

    @Override
    public List<Habit> getHabit() {
        return null;
    }

    @Override
    public List<Habit> getHabits() {
        return null;
    }

    @Override
    public void addHabit(Habit habit) {

    }

    @Override
    public void deleteHabit(String id) {

    }

    @Override
    public void updateHabit(Habit habit) {
    }


    private Map<String, Habit> loadHabits() {
        return null;
    }

    private void saveHabits(Map<String, Habit> habitMap) {

    }

    private Gson getGson() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();
    }
}
