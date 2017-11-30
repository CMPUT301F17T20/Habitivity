package main.habitivity.activities;

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
    private ArrayList<String> followerRequests = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_list);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewRequests);

        adapter = new FollowRequestViewAdapter(this, new ClickListener() {
            @Override
            public void onPositionClicked(int position) {
                User curUser = UserContainer.getInstance().getUser();
                String userName = followerRequests.get(position);
                User userToView = UserContainer.getInstance().findUser(userName);
                curUser.addFollower(userName);
                userToView.addFollowing(curUser.getUserName());
                curUser.removeFollowerRequest(position);
                adapter.notifyItemRemoved(position);

                ElasticsearchController.UpdateUserTask updateUserTask = new ElasticsearchController.UpdateUserTask();
                updateUserTask.execute(UserContainer.getInstance().getUser());
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
