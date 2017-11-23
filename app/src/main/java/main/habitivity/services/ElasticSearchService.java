package main.habitivity.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.searchbox.client.JestClient;
import io.searchbox.core.Delete;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import main.habitivity.Users.Identifiable;

/**
 * Created by Shally on 2017-11-22.
 */

public class ElasticSearchService<User extends Identifiable> {
    private String index;
    private String type;
    private Class<User> typeClass;
    private JestClient jestClient;

    private ElasticSearchService(JestClient jestClient, String index, String type, Class<User> classType) {
        this.jestClient = jestClient;
        this.index = index;
        this.type = type;
        this.typeClass = classType;
    }


    public List<User> getAll() {
        return search("{ \"size\" : " + 100 +" } ");
    }


    public List search(String query) {
        Search search = new Search.Builder(query)
                .addIndex(index)
                .addType(type)
                .build();


        SearchResult result = null;
        try {
            result = jestClient.execute(search);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (result.isSucceeded()) {
            return extractItemsFromHits(result.<User>getHits(typeClass));
        }


        return new ArrayList<>();
    }


    public void create(User item) {
        upsertItem(item);
    }

    public void update(User item) {
        upsertItem(item);
    }


    public void delete(String itemId) {
        Delete delete = new Delete.Builder(itemId)
                .index(index)
                .type(type)
                .build();


        try {
            jestClient.execute(delete);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private List<User> extractItemsFromHits(List<SearchResult.Hit<User, Void>> searchHits) {
        List<User> out = new ArrayList<>();
        for(SearchResult.Hit<User, Void> hit : searchHits) {
            out.add(hit.source);
        }

        return out;
    }

    private void upsertItem(User item) {
        Index documentIndex = new Index.Builder(item)
                .index(index)
                .type(type)
                .id(item.getId())
                .build();


        try {
            jestClient.execute(documentIndex);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Builder<User extends Identifiable> {
        private String index;
        private String type;
        private Class<User> typeClass;
        private JestClient jestClient;

        public ElasticSearchService<User> build() {
            return new ElasticSearchService<>(jestClient, index, type, typeClass);
        }

        public Builder<User> setIndex(String index) {
            this.index = index;
            return this;
        }

        public Builder<User> setType(String type) {
            this.type = type;
            return this;
        }

        public Builder<User> setTypeClass(Class<User> typeClass) {
            this.typeClass = typeClass;
            return this;
        }

        public Builder<User> setJestClient(JestClient jestClient) {
            this.jestClient = jestClient;
            return this;
        }
    }
}
