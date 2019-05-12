package com.satish.nytimesmvvm.di;

import com.satish.nytimesmvvm.MainActivity;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {NetworkModule.class, ViewModelModule.class})
public interface NetworkComponent {
    void injectMainActivity(MainActivity mainActivity);
}
