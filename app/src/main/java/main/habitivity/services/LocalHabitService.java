package main.habitivity.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
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
        return new ArrayList<>(loadHabits().values());
    }

    @Override
    public void addHabit(Habit habit) {
        Map<String, Habit> habits = loadHabits();

        if (!habits.containsKey(habit.getId())) {
            habits.put(habit.getId(), habit);
            saveHabits(habits);
        }
    }

    @Override
    public void deleteHabit(String id) {
        Map<String, Habit> habits = loadHabits();

        if (habits.containsKey(id)) {
            habits.remove(id);
            saveHabits(habits);
        }

    }

    @Override
    public void updateHabit(Habit habit) {
        Map<String, Habit> habits = loadHabits();

        if (habits.containsKey(habit.getId())) {
            habits.put(habit.getId(), habit);
            saveHabits(habits);
        }
    }


    private Map<String, Habit> loadHabits() {
        String serializedHabits = fileHandler.loadFileAsString("testSaveFileForHabits");

        if (serializedHabits.isEmpty() || serializedHabits == null) {
            return new HashMap<>();
        } else {
            return getGson().fromJson(serializedHabits, new TypeToken<Map<String, Habit>>() {}.getType());
        }
    }

    private void saveHabits(Map<String, Habit> habitMap) {
        String serializedHabits = getGson().toJson(habitMap);
        fileHandler.saveStringToFile("testSaveFileForHabits", serializedHabits);
    }

    private Gson getGson() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();
    }
}
