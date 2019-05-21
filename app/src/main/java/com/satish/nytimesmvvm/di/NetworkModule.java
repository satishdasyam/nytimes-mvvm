package com.satish.nytimesmvvm.di;

import android.app.Application;
import android.content.Context;
import androidx.room.Room;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.satish.nytimesmvvm.BuildConfig;
import com.satish.nytimesmvvm.repository.NyRepository;
import com.satish.nytimesmvvm.repository.local.AppDatabase;
import com.satish.nytimesmvvm.repository.remote.ApiHelper;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class NetworkModule {
    private final Application appContext;

    public NetworkModule(Application application) {
        appContext = application;
    }

    @Provides
    @Singleton
    ApiHelper providesRetrofit() {
        /*HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();*/
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).
                addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //.client(client)
                .build();
        return retrofit.create(ApiHelper.class);
    }

    @Provides
    @Singleton
    NyRepository providesArticleRepository(ApiHelper apiService, AppDatabase appDatabase) {
        return new NyRepository(apiService, appDatabase.articleDao());
    }

    @Provides
    @Singleton
    @Named("ApplicationContext")
    Context providesContext() {
        return appContext;
    }

    @Provides
    @Singleton
    AppDatabase providesAppDatabase(@Named("ApplicationContext") Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class, "articles").build();
    }
}
