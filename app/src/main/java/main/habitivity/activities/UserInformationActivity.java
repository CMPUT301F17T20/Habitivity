package main.habitivity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.concurrent.ExecutionException;

import main.habitivity.R;
import main.habitivity.controllers.ElasticsearchController;
import main.habitivity.users.User;
import main.habitivity.users.UserContainer;

public class UserInformationActivity extends BaseActivity {

    private TextView userName;
    private ToggleButton following;
    private ToggleButton Follower;
    private User loggedInUser;
    private User userToView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        loggedInUser = UserContainer.getInstance().getUser();
        userToView = UserContainer.getInstance().getUserToView();

        userName = (TextView) findViewById(R.id.userName);
        userName.setText(userToView.getUserName());

        following = (ToggleButton) findViewById(R.id.followButton);

        for (User user : loggedInUser.getFollowing()) {
            if (user.getUserName().equals(userToView.getUserName())) {
                following.setChecked(true);
            }
        }

        following.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    //loggedInUser.addFollowerRequest(userToView.getUserName());
                    userToView.addFollowerRequest(loggedInUser.getUserName());
                    UserContainer.getInstance().setUser(loggedInUser);
                } else {
                    loggedInUser.deleteFollowing(userToView.getUserName());
                    //userToView.deleteFollower(loggedInUser);
                    UserContainer.getInstance().setUser(loggedInUser);
                }
            }
        });
    }

    @Override
    public void onBackPressed(){
            ElasticsearchController.UpdateUserTask updateUserTask = new ElasticsearchController.UpdateUserTask();
        try {
            updateUserTask.execute(userToView).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(getApplicationContext(), FindFriendsActivity.class);
            startActivity(intent);
    }
}
