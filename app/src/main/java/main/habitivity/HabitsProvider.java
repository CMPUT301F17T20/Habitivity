package main.habitivity;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;

import android.database.Cursor;

import android.net.Uri;
import android.text.TextUtils;

public class HabitsProvider extends ContentProvider {

    public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";

    static final String PROVIDER_NAME = "main.habitivity.HabitsProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/habits";
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String _ID = "_id"; // Table row
    static final String NAME = "name"; // Table column 1
    static final String INFOCOLUMNPLACEHOLDER2 = "info2"; // Table column 2
    static final String INFOCOLUMNPLACEHOLDER3 = "info3"; // Table column 3
    static final String INFOCOLUMNPLACEHOLDER4 = "info4"; // Table column 4

    private static HashMap<String, String> HABITS_MAP; // Table, Key

    static final int HABITS = 1;
    static final int HABITS_ID = 2;

    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "habits", HABITS);
        uriMatcher.addURI(PROVIDER_NAME, "habits/#", HABITS_ID);
    }

    // Implements ContentProvider.query()
    public Cursor query(
            Uri uri,
            String[] projection,
            String selection,
            String[] selectionArgs,
            String sortOrder) {

        /*
         * Choose the table to query and a sort order based on the code returned for the incoming
         * URI. Here, too, only the statements for table 3 are shown.
         */
        switch (UriMatcher.match(uri)) {


            // If the incoming URI was for all of table3
            case 1:

                if (TextUtils.isEmpty(sortOrder)) sortOrder = "_ID ASC";
                break;

            // If the incoming URI was for a single row
            case 2:

                /*
                 * Because this URI was for a single row, the _ID value part is
                 * present. Get the last path segment from the URI; this is the _ID value.
                 * Then, append the value to the WHERE clause for the query
                 */
                selection = selection + "_ID = " + uri.getLastPathSegment();
                break;

            default:
                /**
                 *
                 * ToDo: implement error handling.
                 *
                 */
        }

        /**
         *
         * ToDo: implement code to actually execute the query
         *
         */
    }

    // Implements the provider's insert method
    public Cursor insert(Uri uri, ContentValues values) {

        /**
         *
         *
         * ToDo: implement code to determine which table to open, handle error-checking, etc
         *
         *
         */

        /**
         *
         *
         * ToDo: implement code to grab/get a writeable database,
         * ToDo: and trigger its creation if it doesn't exist.
         *
         *
         */

    }
}
