package main.habitivity.activities;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

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

        for(User user: loggedInUser.getFollowers()){
            if(user.getUserName().equals(userToView.getUserName())) {
                following.setChecked(true);
            }
        }

        following.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    loggedInUser.addFollowing(userToView);
                    UserContainer.getInstance().setUser(loggedInUser);
                }
                else{
                    loggedInUser.deleteFollowing(userToView);
                    UserContainer.getInstance().setUser(loggedInUser);
                }
                ElasticsearchController.UpdateUserTask updateUserTask = new ElasticsearchController.UpdateUserTask();
                updateUserTask.execute(loggedInUser);
            }
        });
    }
}
