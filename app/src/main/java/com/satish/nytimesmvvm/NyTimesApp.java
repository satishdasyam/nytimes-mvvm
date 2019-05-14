package com.satish.nytimesmvvm;

import android.app.Application;
import android.os.StrictMode;
import com.satish.nytimesmvvm.di.DaggerNetworkComponent;
import com.satish.nytimesmvvm.di.NetworkComponent;
import com.satish.nytimesmvvm.di.NetworkModule;

public class NyTimesApp extends Application {

    private NetworkComponent networkComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()
                    .penaltyLog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .detectActivityLeaks()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
        }

    }

    public NetworkComponent getNetworkComponent() {
        if (networkComponent == null) {
            networkComponent = DaggerNetworkComponent.builder()
                    .networkModule(new NetworkModule(this))
                    .build();
        }
        return networkComponent;
    }

}
