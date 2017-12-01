package main.habitivity.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Shally on 2017-12-01.
 */

public class ConnectivityService implements IConnectivityService {
    private Context context;
    private OnConnectivityChangedListener onConnectivityChangedListener;

    /**
     * Instantiates a new Android connectivity service.
     *
     * @param context the context
     */
    public ConnectivityService(Context context) {
        this.context = context;
        registerConnectivityListener();
    }

    @Override
    public boolean isInternetAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    public void setOnConnectivityChangedListener(OnConnectivityChangedListener onConnectivityChangedListener) {
        this.onConnectivityChangedListener = onConnectivityChangedListener;
        dispatchConnectivityChange();
    }

    private void registerConnectivityListener() {
        context.registerReceiver(new ConnectivityListener(),
                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    private void dispatchConnectivityChange() {
        if (onConnectivityChangedListener != null) {
            onConnectivityChangedListener.onConnectivityChanged(isInternetAvailable());
        }
    }

    private class ConnectivityListener extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            dispatchConnectivityChange();
        }
    }
}