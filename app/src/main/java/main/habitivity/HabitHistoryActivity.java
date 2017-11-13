package main.habitivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.habitivity.controllers.HabitListController;
import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitEvent;

public class HabitHistoryActivity extends BaseActivity {

    public ArrayAdapter<HabitEvent> adapter;
    public ListView listView;
    private HabitListController habitListController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_history);

        resolveDependencies();
        adapter = new ArrayAdapter<HabitEvent>(this, android.R.layout.simple_list_item_1, android.R.id.text1, habitListController.getHabitEvents() );
        listView.setAdapter(adapter);
    }

    private void resolveDependencies() {
        HabitApplication app = getHabitApplication();
        habitListController = app.getHabitListController();
    }
}
