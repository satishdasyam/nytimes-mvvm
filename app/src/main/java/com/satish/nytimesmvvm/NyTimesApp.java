package com.satish.nytimesmvvm;

import android.app.Application;
import com.satish.nytimesmvvm.di.DaggerNetworkComponent;
import com.satish.nytimesmvvm.di.NetworkComponent;
import com.satish.nytimesmvvm.di.NetworkModule;

public class NyTimesApp extends Application {

    private NetworkComponent networkComponent;

    @Override
    public void onCreate() {
        super.onCreate();
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
