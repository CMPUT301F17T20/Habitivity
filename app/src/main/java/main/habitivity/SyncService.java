package main.habitivity;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Service that returns an IBinder for the SyncAdapter.
 */
public class SyncService extends Service {

    private static SyncAdapter sSyncAdapter = null; // Temp instance of sync adapter
    private static final Object sSyncAdapterLock = new Object(); // Occupies thread

    @Override
    public void onCreate() {
        /*
         * Set the sync adapter as syncable, and disallows parallel syncs
         */
        synchronized (sSyncAdapterLock) {
            if (sSyncAdapter == null) {
                sSyncAdapter = new SyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        /*
         * Retrieves object that allows external processes to call
         * onPerformSync(), and initiate the SyncAdapter.
         */
        return sSyncAdapter.getSyncAdapterBinder();
    }
}