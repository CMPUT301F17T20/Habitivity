package main.habitivity.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import main.habitivity.R;
import main.habitivity.controllers.AllUsersController;
import main.habitivity.controllers.HabitListController;
import main.habitivity.users.User;

public class Followers extends BaseActivity {
    private ArrayAdapter<User> adapter;
    private ListView listView;
    private AllUsersController allUsersController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);

        resolveDependencies();
        listView = (ListView) findViewById(R.id.list);
        adapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1, android.R.id.text1, allUsersController.getUsersFollowers() );
        listView.setAdapter(adapter);
        //Listens for when a record in the list is pressed
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private void resolveDependencies() {
        HabitApplication app = getHabitApplication();
        allUsersController = app.getAllUserController();
    }
}
