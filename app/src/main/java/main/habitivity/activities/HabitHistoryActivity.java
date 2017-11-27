/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */
package main.habitivity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import main.habitivity.R;
import main.habitivity.controllers.HabitListController;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.HabitSingletonContainer;

public class HabitHistoryActivity extends BaseActivity {

    private ArrayAdapter<HabitEvent> adapter;
    private ListView listView;
    private HabitListController habitListController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_history);

        resolveDependencies();
        listView = (ListView) findViewById(R.id.list);
        adapter = new ArrayAdapter<HabitEvent>(this, android.R.layout.simple_list_item_1, android.R.id.text1, habitListController.getHabitEvents() );
        listView.setAdapter(adapter);
        //Listens for when a record in the list is pressed
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), HabitEventDetailsActivity.class);
                HabitSingletonContainer.getInstance().setHabitEvent((HabitEvent)listView.getAdapter().getItem(position));
                startActivity(intent);
            }
        });
    }

    private void resolveDependencies() {
        HabitApplication app = getHabitApplication();
        habitListController = app.getHabitListController();
    }
}
