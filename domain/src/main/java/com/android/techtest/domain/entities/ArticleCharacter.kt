package com.android.techtest.domain.entities

data class ArticleCharacter(
    val results: List<ResultCharacter>
)

data class ResultCharacter(
    val abstract: String,
    val byline: String,
    val id: Long,
    val media: List<MediaCharacter>,
    val publishedDate: String,
    val section: String,
    val title: String,
    val url: String,
)

data class MediaCharacter(
    val mediaMetadata: List<MediaMetadataCharacter>
)

data class MediaMetadataCharacter(
    val url: String,
)