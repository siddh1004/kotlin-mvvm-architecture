package com.example.nasaious.data.remote.response

import com.example.nasaious.data.local.entity.ArticleEntity
import com.example.nasaious.data.remote.util.RoomMapper

data class ArticleResponse(
    val author: String?,
    val title: String,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?,
) : RoomMapper<ArticleEntity> {
    override fun mapToRoomEntity() = ArticleEntity(
        author, title, description, url, urlToImage, publishedAt, content
    )
}