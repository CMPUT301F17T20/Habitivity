package main.habitivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

import main.habitivity.controllers.HabitListController;
import main.habitivity.controllers.UpdateHabitController;
import main.habitivity.habits.HabitEvent;
import main.habitivity.habits.HabitSingletonContainer;

public class HabitEventDetailsActivity extends BaseActivity {

    private TextView habitEventTitle;
    private TextView completionDate;
    private TextView comment;
    private HabitEvent curHabitEvent;
    private UpdateHabitController updateHabitController;
    private HabitListController habitListController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_event_details_new);
        resolveDependencies();

        habitEventTitle = (TextView) findViewById(R.id.habitEvent);
        completionDate = (TextView) findViewById(R.id.dateChoice);
        comment = (TextView) findViewById(R.id.addComment);

        curHabitEvent = HabitSingletonContainer.getInstance().getHabitEvent();

        String commentString = "Comment: " + curHabitEvent.getComment();
        comment.setText(commentString);
        habitEventTitle.setText(curHabitEvent.getId());
    }

    private void resolveDependencies() {
        HabitApplication app = getHabitApplication();
        updateHabitController = app.getUpdateHabitController();
        habitListController = app.getHabitListController();
    }

    public void onEdit(View view){
        Intent intent = new Intent(getApplicationContext(), HabitivityMain.class);
        habitEventTitle = (TextView) findViewById(R.id.habitEvent);
        completionDate = (TextView) findViewById(R.id.dateChoice);
        comment = (TextView) findViewById(R.id.addComment);

        HabitEvent habitEvent = new HabitEvent(new Date());
        habitEvent.setId(habitEventTitle.getText().toString());
        //fake it out for now
        habitEvent.setCompletionDate(new Date());
        habitEvent.setComment(comment.getText().toString());

        updateHabitController.updateHabitEvent(habitEvent);
        HabitSingletonContainer.getInstance().setHabitEvent(habitEvent);
        startActivity(intent);
    }

    public void onDelete(View view){
        Intent intent = new Intent(getApplicationContext(), HabitivityMain.class);
        habitListController.removeHabitEvent(HabitSingletonContainer.getInstance().getHabitEvent());
        startActivity(intent);
    }
}

