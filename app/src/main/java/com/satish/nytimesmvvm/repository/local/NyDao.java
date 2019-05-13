package com.satish.nytimesmvvm.repository.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.satish.nytimesmvvm.pojo.Article;

import java.util.List;

@Dao
public interface NyDao {
    @Query("SELECT * FROM article_table")
    LiveData<List<Article>> getArticleListLive();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Article> articles);

    @Query("DELETE FROM article_table")
    void deleteTable();
}
