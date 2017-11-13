package main.habitivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import main.habitivity.habits.Habit;

public class HabitListActivity extends AppCompatActivity {

    public ArrayAdapter<Habit> adapter;
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_list);
        listView = (ListView) findViewById(R.id.list);
       // adapter = new ArrayAdapter<Habit>(this, android.R.layout.simple_list_item_1, android.R.id.text1, );
        listView.setAdapter(adapter);
    }
}
