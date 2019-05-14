package com.satish.nytimesmvvm.repository;

import android.util.Log;
import androidx.lifecycle.LiveData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipkart.zjsonpatch.JsonPatch;
import com.here.oksse.OkSse;
import com.here.oksse.ServerSentEvent;
import com.satish.nytimesmvvm.pojo.Article;
import com.satish.nytimesmvvm.pojo.ArticleResponse;
import com.satish.nytimesmvvm.repository.local.NyDao;
import com.satish.nytimesmvvm.repository.remote.ApiHelper;
import com.satish.nytimesmvvm.util.Util;
import io.reactivex.disposables.CompositeDisposable;
import okhttp3.Request;
import okhttp3.Response;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

public class NyRepository {

    private static final String TAG = NyRepository.class.getSimpleName();
    //As we work on 'Server sent event' we doesn't use retrofit
    private final ApiHelper apiHelper;
    private final NyDao nyDao;
    private ServerSentEvent serverSentEvent;
    private JsonNode sourceNode;
    private final ObjectMapper jsonMapper = new ObjectMapper();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Inject
    public NyRepository(ApiHelper apiHelper, NyDao nyDao) {
        this.apiHelper = apiHelper;
        this.nyDao = nyDao;
    }

    /*public void fetchArticleMetadata() {
        Disposable disposable = apiHelper.fetchArticles()
                .subscribeOn(Schedulers.from(Executors
                        .newFixedThreadPool(Util.getNO_OF_THREADS())))
                .subscribe(new Consumer<ArticleResponse>() {
                    @Override
                    public void accept(ArticleResponse articleMetadata) throws Exception {
                        nyDao.deleteTable();
                        nyDao.insertAll(articleMetadata.getArticleList());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, throwable.getMessage());
                    }
                });
        compositeDisposable.add(disposable);
    }*/

    public LiveData<List<Article>> getArticleList() {
        return nyDao.getArticleListLive();
    }

    /**
     * We get real time data without polling from our  data proxy server with single http connection
     */
    private void getStreamingData() {
        /*HttpUrl.Builder builder = HttpUrl.parse(path).newBuilder();
        builder.addQueryParameter("api-key", Util.NYT_KEY);*/
        OkSse okSse = new OkSse();
        serverSentEvent = okSse.newServerSentEvent(getSSERequest(), sseListener);
    }

    private Request getSSERequest() {
        final String path = "https://streamdata.motwin.net/" +
                "https://api.nytimes.com/svc/news/v3/content/nyt/all.json" +
                "?api-key=KXnAjeKj3y6bS8OknsQxer3tNaAGDXwA";
        return new Request.Builder().url(path)
                .addHeader("X-Sd-Token", Util.STREAM_DATA_KEY)
                //.addHeader("Content-Type","text/event-stream")
                .build();
    }


    public void openSSEConnection() {
        getStreamingData();
    }

    public void closeConnection() {
        if (serverSentEvent != null) {
            serverSentEvent.close();
            serverSentEvent = null;
        }
    }


    private ServerSentEvent.Listener sseListener = new ServerSentEvent.Listener() {
        @Override
        public void onOpen(ServerSentEvent sse, Response response) {

        }

        @Override
        public void onMessage(ServerSentEvent sse, String id, String event, String message) {

            try {
                ArticleResponse articleResponse = jsonMapper
                        .readValue(message, ArticleResponse.class);
                if ("data".equals(event)) {
                    // SSE message is a snapshot
                    sourceNode = jsonMapper.readTree(message);
                    nyDao.deleteTable();
                    nyDao.insertAll(articleResponse.getArticleList());
                } else if ("patch".equals(event)) {
                    // SSE message is a patch
                    JsonNode patchNode = jsonMapper.readTree(message);
                    sourceNode = JsonPatch.apply(patchNode, sourceNode);
                    ArticleResponse totalResponse = jsonMapper
                            .readValue(sourceNode.asText(), ArticleResponse.class);
                    nyDao.deleteTable();
                    nyDao.insertAll(totalResponse.getArticleList());
                    Log.d(TAG, "patch:" + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onComment(ServerSentEvent sse, String comment) {

        }

        @Override
        public boolean onRetryTime(ServerSentEvent sse, long milliseconds) {
            return false;
        }

        @Override
        public boolean onRetryError(ServerSentEvent sse, Throwable throwable, Response response) {
            return false;
        }

        @Override
        public void onClosed(ServerSentEvent sse) {

        }

        @Override
        public Request onPreRetry(ServerSentEvent sse, Request originalRequest) {
            return getSSERequest();
        }
    };

    public void onClear() {
        compositeDisposable.dispose();
        closeConnection();
    }
}
