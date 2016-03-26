package com.trackwheels.kinvey;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.kinvey.android.Client;

/**
 * Created by upendra on 3/26/16.
 */
public class KinveyClient extends MultiDexApplication {
    private Client client;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        initialize();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void initialize() {
        // Enter your app credentials here
        client = new Client.Builder(getApplicationContext()).build();
    }

    public Client getKinveyClient() {
        return client;
    }
}
