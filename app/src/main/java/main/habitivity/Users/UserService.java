package main.habitivity.Users;

import java.util.ArrayList;
import java.util.List;

import main.habitivity.BaseActivity;
import main.habitivity.HabitApplication;
import main.habitivity.habits.HabitRepository;
import main.habitivity.services.ElasticSearchService;
import main.habitivity.services.LocalHabitService;

/**
 * Created by Shally on 2017-11-22.
 */

public class UserService extends BaseActivity implements IUserService
{
    private ElasticSearchService<User> elasticSearchService;
    private IUserPreference userPrefs;
    private HabitApplication habitApp = (HabitApplication) getApplication();

    public UserService(ElasticSearchService<User> elasticSearchService) {
        this.elasticSearchService = elasticSearchService;
    }


    @Override
    public User getUser(String username) {

        String query =  "{\n" +
                "    \"query\" : {\n" +
                "        \"term\" : { \"userID\" : \"" + username.toLowerCase() + "\" }\n" +
                "    }\n" +
                "}";
        List<User> userlist = elasticSearchService.search(query);
        if (userlist.size() > 0) {
            return userlist.get(0);
        }
        return new User(username, habitApp.getHabitRepository(), new ArrayList<User>());
    }

    @Override
    public void createUser(User user){

        this.getUser(user.getUsername());

    }

    @Override
    public User getCurrentUser() {
        return userPrefs.getUser();
    }

    @Override
    public void setCurrentUser(User user) {
        userPrefs.saveUser(user);
    }

    @Override
    public void updateCurrentUser(User user) {
        userPrefs.saveUser(user);
        this.updateUser(user);
    }

    @Override
    public void updateUser(User user) {
        elasticSearchService.update(user);
    }
}