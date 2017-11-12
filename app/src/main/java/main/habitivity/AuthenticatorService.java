package main.habitivity;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.net.Authenticator;


public abstract class AuthenticatorService extends Service {
    // Instance field that stores the authenticator object
    private Authenticator serverAuthenticator;
    @Override
    public void onCreate() {
        // Create a new authenticator object
        //serverAuthenticator = new Authenticator(this);
    }


}