package main.habitivity;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;


public class SyncAdapter extends AbstractThreadedSyncAdapter {
    ContentResolver habitContentResolver;

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);

        /*
         * Retrieves instance of HabitsProvider and performs CRUD operations
         */
        habitContentResolver = context.getContentResolver();
    }

    /*
    * Code to be used in the sync adapter. The entire sync adapter runs in a
    * background thread, so we don't need to set up background processing.
    */
    @Override
    public void onPerformSync(
            Account account,
            Bundle extras,
            String authority,
            ContentProviderClient provider,
            SyncResult syncResult) {

        /**
         *
         *
         * ToDo: implementation of data synchronization between client and server.
         *
         *
         */
    }


}
