package com.satish.nytimesmvvm.repository;

import android.util.Log;
import androidx.lifecycle.LiveData;
import com.satish.nytimesmvvm.pojo.Article;
import com.satish.nytimesmvvm.pojo.ArticleResponse;
import com.satish.nytimesmvvm.repository.local.NyDao;
import com.satish.nytimesmvvm.repository.remote.ApiHelper;
import com.satish.nytimesmvvm.util.Util;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.Executors;

public class NyRepository {

    private static final String TAG = NyRepository.class.getSimpleName();

    private final ApiHelper apiHelper;
    private final NyDao nyDao;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Inject
    public NyRepository(ApiHelper apiHelper, NyDao nyDao) {
        this.apiHelper = apiHelper;
        this.nyDao = nyDao;
    }

    public void fetchArticleMetadata() {
        Disposable disposable = apiHelper.fetchArticles()
                .subscribeOn(Schedulers.from(Executors
                        .newFixedThreadPool(Util.getNO_OF_THREADS())))
                .subscribe(new Consumer<ArticleResponse>() {
                    @Override
                    public void accept(ArticleResponse articleMetadata) throws Exception {
                        nyDao.deleteTable();
                        nyDao.insertAll(articleMetadata.articleList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, throwable.getMessage());
                    }
                });
        compositeDisposable.add(disposable);
    }

    public LiveData<List<Article>> getArticleList() {
        return nyDao.getArticleListLive();
    }

    public void onClear() {
        compositeDisposable.dispose();
    }
}
