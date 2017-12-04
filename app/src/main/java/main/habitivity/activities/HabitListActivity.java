/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */
package main.habitivity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import main.habitivity.R;
import main.habitivity.adapters.AllHabitsViewAdapter;
import main.habitivity.adapters.ClickListener;
import main.habitivity.adapters.FollowRequestViewAdapter;
import main.habitivity.controllers.ElasticsearchController;
import main.habitivity.controllers.HabitListController;
import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.HabitSingletonContainer;
import main.habitivity.users.User;
import main.habitivity.users.UserContainer;

public class HabitListActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private AllHabitsViewAdapter adapter;
    private HabitListController habitListController;
    private List<Habit> habits = new ArrayList<Habit>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_list);
        resolveDependencies();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewAllHabits);
        adapter = new AllHabitsViewAdapter(this, new ClickListener() {
            @Override
            public void onPositionClicked(int position) {
                Intent intent = new Intent(getApplicationContext(), JustHabitDetails.class);
                HabitSingletonContainer.getInstance().setHabit(habitListController.getHabits().get(position));
                startActivity(intent);
            }
        });
        adapter.setRequestList(habitListController.getHabits());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
    }

    private void resolveDependencies() {
        HabitApplication app = getHabitApplication();
        habitListController = app.getHabitListController();
    }
}
