/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */
package main.habitivity.services;

import main.habitivity.controllers.ElasticsearchController;
import main.habitivity.exceptions.ElasticSearchConnectivityException;
import main.habitivity.users.UserContainer;

/**
 * TODO. WILL BE IMPLEMENTED WHEN WE DO ELASTIC SEARCH
 */
public class WhichHabitService {
    private IConnectivityService connectivityService;
    public WhichHabitService(IConnectivityService connectivityService) {
        this.connectivityService = connectivityService;
    }

    public void addHabitEventRequest(){
        try {
            ElasticsearchController.UpdateUserTask updateUserTask = new ElasticsearchController.UpdateUserTask();
            updateUserTask.execute(UserContainer.getInstance().getUser());
        }
        catch(ElasticSearchConnectivityException e){

        }

    }

    public void deleteHabitEventRequest(){
        try {
            ElasticsearchController.UpdateUserTask updateUserTask = new ElasticsearchController.UpdateUserTask();
            updateUserTask.execute(UserContainer.getInstance().getUser());
        }
        catch(ElasticSearchConnectivityException e){

        }

    }

    public void updateHabitEventRequest(){
        try {
            ElasticsearchController.UpdateUserTask updateUserTask = new ElasticsearchController.UpdateUserTask();
            updateUserTask.execute(UserContainer.getInstance().getUser());
        }
        catch(ElasticSearchConnectivityException e){

        }

    }

}
