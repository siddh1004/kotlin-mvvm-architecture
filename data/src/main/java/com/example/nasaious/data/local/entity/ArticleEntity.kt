package com.example.nasaious.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nasaious.data.local.database.ARTICLE_TABLE_NAME
import com.example.nasaious.data.remote.util.DomainMapper
import com.example.nasaious.domain.model.Article

@Entity(tableName = ARTICLE_TABLE_NAME)
data class ArticleEntity(
    val author: String?,
    @PrimaryKey
    val title: String,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?,
) : DomainMapper<Article> {

    override fun mapToDomainModel() = Article(
        author, title, description, url, urlToImage, publishedAt, content
    )
}