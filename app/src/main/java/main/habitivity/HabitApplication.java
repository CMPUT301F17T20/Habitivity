package main.habitivity;

import android.app.Application;

import main.habitivity.controllers.AddHabitController;
import main.habitivity.controllers.HabitDetailsController;
import main.habitivity.controllers.HabitListController;
import main.habitivity.habits.HabitRepository;
import main.habitivity.habits.IHabitRepository;
import main.habitivity.interactions.Clock;
import main.habitivity.interactions.HabitInteractionsFactory;
import main.habitivity.services.AndroidFileHandler;
import main.habitivity.services.IHabitService;
import main.habitivity.services.LocalHabitService;


/**
 * This class exposes dependencies to the rest of the system. It takes
 * care of the lifetime for each dependency, so all the consumers
 * need to care about is what dependency they need.
 */
public class HabitApplication extends Application {
    private HabitRepository habitRepository;
    private HabitInteractionsFactory habitInteractionsFactory;

    public HabitRepository getHabitRepository() {
        ensureHabitRepository();
        return habitRepository;
    }

    private void ensureHabitRepository() {
        if (habitRepository == null) {
            AndroidFileHandler fileHandler = new AndroidFileHandler(this);
            LocalHabitService habitService = new LocalHabitService(fileHandler);
            habitRepository = new HabitRepository(habitService);
        }
    }

    public HabitInteractionsFactory getHabitInteractionsFactory() {
        ensureHabitInteractionsFactory();
        return habitInteractionsFactory;
    }

    private void ensureHabitInteractionsFactory() {
        if (habitInteractionsFactory == null) {
            habitInteractionsFactory = new HabitInteractionsFactory(getHabitRepository(), new Clock());
        }
    }

    public HabitListController getHabitListController() {
        return new HabitListController(getHabitInteractionsFactory());
    }
//
//    public TodaysHabitsController getTodaysHabitsController() {
//        return new TodaysHabitsController(getHabitRepository(), getHabitInteractionsFactory());
//    }

    public HabitDetailsController getHabitDetailsController(String habitId) {
        return new HabitDetailsController(getHabitRepository(), getHabitInteractionsFactory(), habitId);
    }

    public AddHabitController getAddHabitController() {
        return new AddHabitController(getHabitInteractionsFactory());
    }
}