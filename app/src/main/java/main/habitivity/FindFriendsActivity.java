/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */
package main.habitivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import main.habitivity.controllers.AllUsersController;
import main.habitivity.controllers.HabitListController;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.HabitSingletonContainer;
import main.habitivity.users.User;

public class FindFriendsActivity extends BaseActivity {
    private ArrayAdapter<User> adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friends);

        listView = (ListView) findViewById(R.id.list);
        adapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1, android.R.id.text1, AllUsersController.getAllUsers() );
        listView.setAdapter(adapter);
        //Listens for when a record in the list is pressed
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }
}
