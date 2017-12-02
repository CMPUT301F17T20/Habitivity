/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */
package main.habitivity.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import main.habitivity.R;
import main.habitivity.adapters.ClickListener;
import main.habitivity.adapters.FollowRequestViewAdapter;
import main.habitivity.adapters.HabitHistoryViewAdapter;
import main.habitivity.controllers.ElasticsearchController;
import main.habitivity.controllers.HabitListController;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.HabitSingletonContainer;
import main.habitivity.users.User;
import main.habitivity.users.UserContainer;

public class HabitHistoryActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private HabitHistoryViewAdapter adapter;
    private List<HabitEvent> habitEvents = new ArrayList<HabitEvent>();


    //private ArrayAdapter<HabitEvent> adapter;
    //private ListView listView;
    private HabitListController habitListController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_events);

        resolveDependencies();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewEvents);

        adapter = new HabitHistoryViewAdapter(this, new ClickListener() {
            @Override
            public void onPositionClicked(int position) {
                Intent intent = new Intent(getApplicationContext(), HabitEventDetailsActivity.class);
                HabitSingletonContainer.getInstance().setHabitEvent(habitEvents.get(position));
                startActivity(intent);
            }
        });
        habitEvents = habitListController.getSortedEvents("");
        adapter.setRequestList(habitEvents);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);


        Button filter = (Button) findViewById(R.id.settingsButton2);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText searchText = (EditText) findViewById(R.id.searchTerm);
                String search = searchText.getText().toString();
                habitEvents = habitListController.getSortedEvents(search);
                adapter.setRequestList(habitEvents);
                adapter.notifyDataSetChanged();

                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow( view.getWindowToken(), 0);
                }
            }
        });
        /*
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
        */
    }

    private void resolveDependencies() {
        HabitApplication app = getHabitApplication();
        habitListController = app.getHabitListController();
    }
}
