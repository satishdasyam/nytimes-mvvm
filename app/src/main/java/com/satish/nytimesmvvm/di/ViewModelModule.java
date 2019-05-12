package com.satish.nytimesmvvm.di;

import androidx.lifecycle.ViewModel;
import com.satish.nytimesmvvm.repository.NyRepository;
import com.satish.nytimesmvvm.view_model.ArticleViewModel;
import com.satish.nytimesmvvm.view_model.ViewModelFactory;
import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

import javax.inject.Provider;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;


//Dagger’s multi-bindings
//https://www.techyourchance.com/dependency-injection-viewmodel-with-dagger-2/

@Module
public class ViewModelModule {

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    @interface ViewModelKey {
        Class<? extends ViewModel> value();
    }

    @Provides
    ViewModelFactory viewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
        return new ViewModelFactory(providerMap);
    }

    @Provides
    @IntoMap
    @ViewModelKey(ArticleViewModel.class)
    ViewModel providesPostViewModel(NyRepository postRepository) {
        return new ArticleViewModel(postRepository);
    }

}