package main.habitivity.controllers;


import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.util.ArrayList;
import java.util.List;

import io.searchbox.client.JestResult;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import main.habitivity.habits.HabitRepository;
import main.habitivity.users.User;
import main.habitivity.users.UserList;

public class ElasticsearchController {
    private static final String indexString = "cmput301f17t20_test7";
    private static JestDroidClient client;
    private static String userTypeString = "users";

    public static class GetUserTask extends AsyncTask<String, Void, User> {
        @Override
        protected User doInBackground(String... username) {
            verifySettings();

            Get get = new Get.Builder(indexString, username[0]).type(userTypeString).build();

            try {
                JestResult result = client.execute(get);

                if(result.isSucceeded()) {
                    User user = result.getSourceAsObject(User.class);
                    return user;

                }
            }
            catch (Exception e) {
                Log.i("Error", "Error when communicating with server");
            }
            return null;
        }
    }

    public static class AddUsersTask extends AsyncTask<User, Void, Void> {

        @Override
        protected Void doInBackground(User... users) {
            verifySettings();

            for (User user : users) {
                Index index = new Index.Builder(user).index(indexString).type(userTypeString).build();

                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        user.setId(result.getId());
                    }
                    else {
                        Log.i("Error", "Cannot add user to server");
                    }
                }
                catch (Exception e) {
                    Log.i("Error", "Exception occurred when trying to add user to server");
                }

            }
            return null;
        }
    }

    public static class UpdateUserTask extends AsyncTask<User, Void, Void> {

        @Override
        protected Void doInBackground(User... users) {
            verifySettings();
            Index index = new Index.Builder(users[0]).index(indexString).type(userTypeString).id(users[0].getId()).build();

            try {
                DocumentResult result = client.execute(index);
            }
            catch (Exception e) {
                Log.i("Error", "Exception occurred when trying to update the user on the server");
            }

            return null;
        }
    }

    public static class GetAllUserTask extends AsyncTask<Void, Void, ArrayList<User>> {
        @Override
        protected ArrayList<User> doInBackground(Void... params) {
            verifySettings();

            ArrayList<User> users = new ArrayList<User>();
            String query = "";

            Search search = new Search.Builder(query)
                    .addIndex(indexString)
                    .addType(userTypeString)
                    .build();
            try {
                SearchResult result = client.execute(search);
                if(result.isSucceeded()) {
                    List<User> foundUsers = result.getSourceAsObjectList(User.class);
                    users.addAll(foundUsers);
                }
            }
            catch (Exception e) {
            }
            return users;
        }
    }

    public static class GetAllUsersWithUserNameTask extends AsyncTask<String, Void, ArrayList<User>> {
        @Override
        protected ArrayList<User> doInBackground(String... search_parameters) {
            verifySettings();

            ArrayList<User> users = new ArrayList<User>();

            Search search = new Search.Builder(search_parameters[0])
                    .addIndex(indexString)
                    .addType(userTypeString)
                    .build();
            try {
                SearchResult result = client.execute(search);
                if(result.isSucceeded()) {
                    List<User> foundUsers = result.getSourceAsObjectList(User.class);
                    users.addAll(foundUsers);
                }
            }
            catch (Exception e) {
            }
            return users;
        }
    }

    public static class CreateHabitRepository extends AsyncTask<HabitRepository, Void, Void> {

        @Override
        protected Void doInBackground(HabitRepository... repos) {
            verifySettings();

            for(HabitRepository repo : repos) {
                Index index = new Index.Builder(repo).index(indexString).type("HabitRepository").build();

                try {
                    DocumentResult execute = client.execute(index);

                    if (execute.isSucceeded()) {
                        repo.setID(execute.getId());
                    }
                } catch (Exception e) {
                    Log.i("Error", "The application failed to build and send the repository");
                }
            }
            return null;
        }
    }

    public static class UpdateHabitRepository extends AsyncTask<HabitRepository, Void, Void> {

        @Override
        protected Void doInBackground(HabitRepository... repos) {
            verifySettings();

            for(HabitRepository repo : repos) {
                Index index = new Index.Builder(repo).index(indexString).type("HabitRepository").id(repo.getID()).build();

                try {
                    DocumentResult execute = client.execute(index);
                } catch (Exception e) {
                    Log.i("Error", "The application failed to build and send the repository");
                }
            }
            return null;
        }
    }

    public static class GetHabitRepository extends AsyncTask<String, Void, HabitRepository> {
        @Override
        protected HabitRepository doInBackground(String... search_parameters) {
            verifySettings();

            HabitRepository repo = null;

            String query = "";

            if (search_parameters[0].length() != 0) {
                query = "{\n" +
                        "    \"query\" : {\n" +
                        "        \"term\" : { \"userID\" : \"" + search_parameters[0] + "\" }\n" +
                        "    }\n" +
                        "}";
            }


            Search search = new Search.Builder(query)
                    .addIndex(indexString).addType("HabitRepository").build();
            try {
               
                SearchResult result = client.execute(search);
                if (result.isSucceeded()) {
                    repo = result.getSourceAsObject(HabitRepository.class);
                }
                else {
                    Log.i("Error", "the search query failed to find repository for given id");
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return repo;
        }
    }

    public static class CreateUserList extends AsyncTask<UserList, Void, Void> {

        @Override
        protected Void doInBackground(UserList... users) {
            verifySettings();

            for(UserList user : users) {
                Index index = new Index.Builder(user).index(indexString).type("UserList").build();

                try {
                    DocumentResult execute = client.execute(index);

                    if (execute.isSucceeded()) {
                        user.setID(execute.getId());
                    }
                } catch (Exception e) {
                    Log.i("Error", "The application failed to build and send the user list");
                }
            }
            return null;
        }
    }

    public static class UpdateUserList extends AsyncTask<UserList, Void, Void> {

        @Override
        protected Void doInBackground(UserList... users) {
            verifySettings();

            for(UserList user : users) {
                Index index = new Index.Builder(user).index(indexString).type("UserList").id(user.getID()).build();

                try {
                    DocumentResult execute = client.execute(index);
                } catch (Exception e) {
                    Log.i("Error", "The application failed to build and send the user list");
                }
            }
            return null;
        }
    }

    public static class GetUserList extends AsyncTask<Void, Void, UserList> {
        @Override
        protected UserList doInBackground(Void... params) {
            verifySettings();

            UserList users = null;
            String query = "";

            Search search = new Search.Builder(query)
                    .addIndex(indexString).addType("UserList").build();
            try {

                SearchResult result = client.execute(search);
                if (result.isSucceeded()) {
                    users = result.getSourceAsObject(UserList.class);
                }
                else {
                    Log.i("Error", "the search query failed to find the user list");
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return users;
        }
    }

    private static void verifySettings() {
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }
}
