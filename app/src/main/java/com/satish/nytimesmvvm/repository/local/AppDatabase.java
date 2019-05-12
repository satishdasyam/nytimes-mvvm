package com.satish.nytimesmvvm.repository.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.satish.nytimesmvvm.pojo.ArticleMetadata;

@Database(entities = {ArticleMetadata.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NyDao articleDao();
}
