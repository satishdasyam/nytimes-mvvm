package com.satish.nytimesmvvm.repository.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.satish.nytimesmvvm.pojo.ArticleMetadata;

import java.util.List;

@Dao
public interface NyDao {
    @Query("SELECT * FROM ArticleMetadata")
    List<ArticleMetadata> getMetaDataList();

    @Insert
    void insertAll(ArticleMetadata... record);

    @Delete
    void delete(ArticleMetadata record);
}
