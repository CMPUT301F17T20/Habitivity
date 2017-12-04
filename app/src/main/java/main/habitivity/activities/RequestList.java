package main.habitivity.activities;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import main.habitivity.R;
import main.habitivity.adapters.ClickListener;
import main.habitivity.adapters.FollowRequestViewAdapter;
import main.habitivity.controllers.ElasticsearchController;
import main.habitivity.users.User;
import main.habitivity.users.UserContainer;

public class RequestList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FollowRequestViewAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ArrayList<String> followerRequests = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_list);

        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayoutRequests);

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

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewRequests);

        adapter = new FollowRequestViewAdapter(this, new ClickListener() {
            @Override
            public void onPositionClicked(int position) {
                User curUser = UserContainer.getInstance().getUser();
                String userName = followerRequests.get(position);
                User userToView = UserContainer.getInstance().findUser(userName);
                if(userToView == null){
                    //something went wrong don't do anything
                    return;
                }
                curUser.addFollower(userName);
                userToView.addFollowing(curUser.getUserName());
                curUser.removeFollowerRequest(position);
                adapter.notifyItemRemoved(position);

                ElasticsearchController.UpdateUserTask updateUserTask = new ElasticsearchController.UpdateUserTask();
                updateUserTask.execute(curUser);

                ElasticsearchController.UpdateUserTask updateUserToViewTask = new ElasticsearchController.UpdateUserTask();
                updateUserToViewTask.execute(userToView);

                UserContainer.getInstance().setUser(curUser);
                UserContainer.getInstance().setUserToView(userToView);
            }
        });
        followerRequests = UserContainer.getInstance().getUser().getFollowerRequests();
        adapter.setRequestList(followerRequests);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
    }



}
