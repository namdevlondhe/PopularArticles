package com.android.techtest.domain.entities

data class ArticleCharacter(
    var results: List<ResultCharacter>
)

data class ResultCharacter(
    var abstract: String,
    var byline: String,
    var id: Long,
    var media: List<MediaCharacter>,
    var publishedDate: String,
    var section: String,
    var title: String,
    var url: String,
)

data class MediaCharacter(
    var mediaMetadata: List<MediaMetadataCharacter>
)

data class MediaMetadataCharacter(
    var url: String,
)