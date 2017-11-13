package main.habitivity.controllers;


import main.habitivity.controllers.IController;
import main.habitivity.interactions.HabitInteractionsFactory;
import main.habitivity.views.IAddHabitView;

public class AddHabitController implements IController<IAddHabitView> {
    private IAddHabitView addHabitView;
    private HabitInteractionsFactory habitInteractionsFactory;

    public AddHabitController(HabitInteractionsFactory habitInteractionsFactory) {
        this.habitInteractionsFactory = habitInteractionsFactory;
    }

    public void addHabit(AddHabitRequest addHabitRequest) {
        habitInteractionsFactory.addHabit().add(addHabitRequest.getId(), addHabitRequest.getStartDate(), addHabitRequest.getDaysOfTheWeek());
        //dispatchOnHabitAdded();
    }

//    private void dispatchOnHabitAdded() {
//        if (addHabitView != null) {
//            addHabitView.onHabitAdded();
//        }
//    }

    @Override
    public void attachView(IAddHabitView view) {
        this.addHabitView = view;
    }

    @Override
    public void detachView() {
        this.addHabitView = null;
    }
}