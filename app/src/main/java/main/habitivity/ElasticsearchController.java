package main.habitivity;


import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import main.habitivity.habits.HabitRepository;
import main.habitivity.services.WhichHabitService;

public class ElasticsearchController {
    private static final String indexString = "CMPUT301F17T20";
    private static JestDroidClient client;

    public static class SaveHabitRepository extends AsyncTask<HabitRepository, Void, Void> {

        @Override
        protected Void doInBackground(HabitRepository... repo) {
            verifySettings();

            Index index = new Index.Builder(repo).index(indexString).type("HabitRepository").build();

            try {
                DocumentResult execute = client.execute(index);
            }
            catch (Exception e) {
                Log.i("Error", "The application failed to build and send the repository");
            }

            return null;
        }
    }

    public static class GetHabitRepository extends AsyncTask<String, Void, HabitRepository> {
        @Override
        protected HabitRepository doInBackground(String... search_parameters) {
            verifySettings();

            HabitRepository repo = new HabitRepository(new WhichHabitService());

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
