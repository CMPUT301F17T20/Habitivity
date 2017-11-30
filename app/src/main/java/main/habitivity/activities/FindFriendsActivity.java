/*
 * Copyright (c) 2017. Team CMPUT301F17T20, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behaviour at University of Alberta.
 */
package main.habitivity.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import main.habitivity.R;
import main.habitivity.adapters.ClickListener;
import main.habitivity.adapters.FindFriendsViewAdapter;
import main.habitivity.adapters.FollowRequestViewAdapter;
import main.habitivity.controllers.AllUsersController;
import main.habitivity.controllers.ElasticsearchController;
import main.habitivity.users.User;
import main.habitivity.users.UserContainer;

public class FindFriendsActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private FindFriendsViewAdapter adapter;
    private ArrayList<String> friends = new ArrayList<>();
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friends);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewFriends);

        builder =new AlertDialog.Builder(this);

        adapter = new FindFriendsViewAdapter(this, new ClickListener() {
            @Override
            public void onPositionClicked(int position) {
                String user = friends.get(position);
                String userName = UserContainer.getInstance().getUser().getPotentialFriends().get(position);
                User userToView = UserContainer.getInstance().findUser(userName);
                User curUser = UserContainer.getInstance().getUser();
                if(userToView == null){
                    //something went wrong. Need to fix
                    return;
                }
                userToView.addFollowerRequest(user);
                curUser.removePotentialFriend(position);
                curUser.addToPendingRequest(userToView.getUserName());

                ElasticsearchController.UpdateUserTask updateUserTask = new ElasticsearchController.UpdateUserTask();
                updateUserTask.execute(curUser);

                ElasticsearchController.UpdateUserTask updateUserToViewTask = new ElasticsearchController.UpdateUserTask();
                updateUserToViewTask.execute(userToView);

                UserContainer.getInstance().setUser(curUser);
                UserContainer.getInstance().setUserToView(userToView);

                adapter.notifyItemRemoved(position);

                builder.setMessage("You've requested to follow: " + user)
                        .setNegativeButton("Okay", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                        .setTitle("Follow Request");
                builder.show();
            }
        });
        friends = UserContainer.getInstance().getUser().getPotentialFriends();
        adapter.setRequestList(friends);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), HabitivityMain.class);
        startActivity(intent);
    }
}
