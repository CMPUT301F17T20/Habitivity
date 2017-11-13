package main.habitivity.controllers;

import java.util.List;

import main.habitivity.habits.Habit;
import main.habitivity.habits.IHabitRepository;
import main.habitivity.interactions.HabitInteractionsFactory;

public class HabitDetailsController implements IController<IHabitDetailsView> {
    private String habitId;
    private IHabitDetailsView habitDetailsView;
    private IHabitRepository habitRepository;
    private HabitInteractionsFactory habitInteractionsFactory;

    public HabitDetailsController(IHabitRepository habitRepository, HabitInteractionsFactory habitInteractionsFactory, String habitId) {
        this.habitId = habitId;
        this.habitRepository = habitRepository;
        this.habitInteractionsFactory = habitInteractionsFactory;
    }

    public void deleteHabit() {
        habitInteractionsFactory.deleteHabit().delete(habitId);
    }

    public void markHabitAsComplete() {
        habitInteractionsFactory.addHabitEvent().complete(habitId);
    }

    public void removeHabitCompletion(String completionId) {
        habitInteractionsFactory.removeHabitEvent().remove(habitId, completionId);
    }


    private Habit getHabitFromHabitList(List<Habit> habits) {
        for (Habit habit: habits) {
            if (habitId.equals(habit.getId())) {
                return habit;
            }
        }
        return null;
    }

    @Override
    public void attachView(IHabitDetailsView view) {
        this.habitDetailsView = view;
    }

    @Override
    public void detachView() {
        this.habitDetailsView = null;
    }

    private void dispatchDisplayHabit(Habit habit) {
        if (habitDetailsView != null) {
            habitDetailsView.displayHabit(habit);
        }
    }
}
