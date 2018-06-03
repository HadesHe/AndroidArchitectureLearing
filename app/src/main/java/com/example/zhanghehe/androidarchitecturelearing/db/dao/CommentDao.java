package com.example.zhanghehe.androidarchitecturelearing.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.zhanghehe.androidarchitecturelearing.db.entity.CommentEntity;

import java.util.List;

@Dao
public interface CommentDao {
    @Query("SELECT * FROM comments where productId=:productId")
    LiveData<List<CommentEntity>> loadComments(int productId);

    @Query("SELECT * FROM comments where productId=:productId")
    List<CommentEntity> loadCommetnsSync(int productId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CommentEntity> comments);
}
