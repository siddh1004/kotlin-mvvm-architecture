package com.example.nasaious.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nasaious.data.local.database.ARTICLE_TABLE_NAME
import com.example.nasaious.data.local.entity.ArticleEntity

@Dao
abstract class NewsDao {
    @Query("SELECT * FROM $ARTICLE_TABLE_NAME")
    abstract fun getArticles(): LiveData<List<ArticleEntity>>

    @Query("SELECT * FROM $ARTICLE_TABLE_NAME Where title = :title")
    abstract fun getArticle(title: String): LiveData<ArticleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveArticles(articles: List<ArticleEntity>)

    @Query("DELETE FROM $ARTICLE_TABLE_NAME")
    abstract fun nukeTable()
}