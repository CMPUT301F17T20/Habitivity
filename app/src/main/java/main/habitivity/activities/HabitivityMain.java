package main.habitivity.activities;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import main.habitivity.R;
import main.habitivity.adapters.ClickListener;
import main.habitivity.adapters.FindFriendsViewAdapter;
import main.habitivity.adapters.HomeFeedViewAdapter;
import main.habitivity.controllers.AllUsersController;
import main.habitivity.controllers.ElasticsearchController;
import main.habitivity.controllers.HabitListController;
import main.habitivity.habits.Habit;
import main.habitivity.habits.HabitSingletonContainer;
import main.habitivity.users.User;
import main.habitivity.users.UserContainer;

public class HabitivityMain extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView userName;
    private ListView listView;
    private RecyclerView recyclerView;
    private HomeFeedViewAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private HabitListController habitListController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habitivity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        resolveDependencies();

        userName = (TextView) findViewById(R.id.User);
        String welcomeUser = "Welcome User: " + UserContainer.getInstance().getUser();
        userName.setText(welcomeUser);

        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayoutMain);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //pull from elastic search
                ArrayList<User> allUsers = new ArrayList<User>();
                ArrayList<User> allusers = UserContainer.getInstance().getAllUsers();
                UserContainer.getInstance().setAllUsers(allusers);
                allUsers = UserContainer.getInstance().getAllUsers();

                String currentUserName =  UserContainer.getInstance().getUser().getUserName();
                ArrayList<User> copyOfAllUsers = new ArrayList<>(allUsers);
                for(User users: copyOfAllUsers){
                    if(users.getUserName().equals(currentUserName)){
                        copyOfAllUsers.remove(users);
                        break;
                    }
                }
                UserContainer.getInstance().setAllUsersExcludingUser(copyOfAllUsers);
                mSwipeRefreshLayout.setRefreshing(false);

                adapter.notifyDataSetChanged();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.listRecyclerView);
        adapter = new HomeFeedViewAdapter(this, new ClickListener() {
            @Override
            public void onPositionClicked(int position) {
                Intent intent = new Intent(getApplicationContext(), EventDetailsNonEditable.class);
                Habit curHabit = habitListController.getAllHabitsOfUserAndFollowing().get(position);
                //get the owner of the habit
                User userToView = UserContainer.getInstance().findUser(curHabit.getUserName());
                HabitSingletonContainer.getInstance().setHabit(curHabit);
                UserContainer.getInstance().setUserToView(userToView);
                startActivity(intent);
            }

        });
        adapter.setRequestList(habitListController.getAllHabitsOfUserAndFollowing());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        Button buttonLogo = (Button) findViewById(R.id.logobutton);
        buttonLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawerLayout.openDrawer(Gravity.START);
            }
        });

        Button settingsButton = (Button) findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);

            }
        });

        Button buttonLeft = (Button) findViewById(R.id.buttonLeft);
        buttonLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                Intent intent = new Intent(HabitivityMain.this, TodaysHabits.class );
                startActivity(intent);
            }
        });

        Button buttonMiddle = (Button) findViewById(R.id.buttonMiddle);
        buttonMiddle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                Intent intent = new Intent(HabitivityMain.this, HabitHistoryActivity.class);
                startActivity(intent);
            }
        });

        Button buttonRight = (Button) findViewById(R.id.buttonRight);
        buttonRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                Intent intent = new Intent(HabitivityMain.this, HabitListActivity.class);
                startActivity(intent);
            }
        });

        Button buttonAdd = (Button) findViewById(R.id.add_habit);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                Intent intent = new Intent(HabitivityMain.this, AddHabitActivity.class);
                startActivity(intent);
            }
        });
    }

    private void resolveDependencies() {
        HabitApplication app = getHabitApplication();
        habitListController = app.getHabitListController();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.habitivity_main, menu);
        return true;
    }

    public void updateUsers(){
        ArrayList<User> allUsers = AllUsersController.getAllUsers();
        UserContainer.getInstance().setAllUsers(allUsers);

        String currentUserName =  UserContainer.getInstance().getUser().getUserName();
        for(User users: allUsers){
            if(users.getUserName().equals(currentUserName)){
                allUsers.remove(users);
                break;
            }
        }
        UserContainer.getInstance().setAllUsersExcludingUser(allUsers);
    }
    public void onRefreshClicked(View view){
        updateUsers();
        adapter.notifyDataSetChanged();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.


        int id = item.getItemId();

        if (id == R.id.add_habit) {
            Intent intent = new Intent(getApplicationContext(), AddHabitActivity.class);
            startActivity(intent);

        } else if (id == R.id.add_event) {
            Intent intent = new Intent(getApplicationContext(), HabitHistoryActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(getApplicationContext(), HabitListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(getApplicationContext(), TodaysHabits.class);
            startActivity(intent);
        }
        else if(id ==R.id.maps){
            Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.find_friends){
            Intent intent = new Intent(getApplicationContext(), FindFriendsActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.requests){
            Intent intent = new Intent(getApplicationContext(), RequestList.class);
            startActivity(intent);
        }
        else if(id == R.id.log_out){
            Intent intent = new Intent(getApplicationContext(), SplashScreen.class);
            startActivity(intent);
        }
        else if(id == R.id.following){
            Intent intent = new Intent(getApplicationContext(), Following.class);
            startActivity(intent);
        }
        else if(id == R.id.followers){
            Intent intent = new Intent(getApplicationContext(), Followers.class);
            startActivity(intent);
        }
        return false;
    }
}
