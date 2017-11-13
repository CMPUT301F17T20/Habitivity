package main.habitivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import main.habitivity.controllers.AddHabitEventController;
import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitSingletonContainer;

public class AddEvent extends BaseActivity {

    private TextView eventTitle;
    private EditText comment;
    private Habit curHabit;
    private AddHabitEventController addHabitEventController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        resolveDependencies();

        curHabit = HabitSingletonContainer.getInstance().getHabit();
        eventTitle = (TextView) findViewById(R.id.habitEvent);
        eventTitle.setText(curHabit.getTitle());

    }

    private void resolveDependencies() {
        HabitApplication app = getHabitApplication();
        addHabitEventController = app.getAddHabitEventsController();
    }

    public void onAdd(View view){
        Intent intent = new Intent(getApplicationContext(), HabitHistoryActivity.class);
        comment = (EditText) findViewById(R.id.addComment);

        addHabitEventController.addHabitEvent(eventTitle.getText().toString(), comment.getText().toString());

        startActivity(intent);
    }

}
