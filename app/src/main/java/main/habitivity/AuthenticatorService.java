package main.habitivity;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.net.Authenticator;


public class AuthenticatorService extends Service {
    // Instance field that stores the authenticator object
    private Authenticator serverAuthenticator;
    @Override
    public void onCreate() {
        // Create a new authenticator object
        serverAuthenticator = new Authenticator(this);
    }

    /*
     * System binds to this Service to make a remote call to a server and
     * return the authenticator's IBinder.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return serverAuthenticator.getIBinder();
    }
}