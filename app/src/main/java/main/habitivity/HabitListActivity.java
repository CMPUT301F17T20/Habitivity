package main.habitivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import main.habitivity.controllers.HabitListController;
import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitRepository;

public class HabitListActivity extends BaseActivity {

    public ArrayAdapter<Habit> adapter;
    public ListView listView;
    private HabitListController habitListController;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_list);
        resolveDependencies();
        listView = (ListView) findViewById(R.id.list);
        adapter = new ArrayAdapter<Habit>(this, android.R.layout.simple_list_item_1, android.R.id.text1, habitListController.getHabits() );
        listView.setAdapter(adapter);
        //Listens for when a record in the list is pressed
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Intent intent = new Intent(getApplicationContext(), HabitDetails.class);
                //set our currentSelectedRecord to the record we tapped on
                //startActivity(intent);
            }
        });

    }

    private void resolveDependencies() {
        HabitApplication app = getHabitApplication();
        habitListController = app.getHabitListController();
    }
}
