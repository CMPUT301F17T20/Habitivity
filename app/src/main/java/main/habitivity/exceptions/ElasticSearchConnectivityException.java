package main.habitivity.exceptions;

/**
 * Created by Shally on 2017-12-01.
 */

public class ElasticSearchConnectivityException extends RuntimeException {
    public ElasticSearchConnectivityException(Exception e) {
        super(e);
    }
}
