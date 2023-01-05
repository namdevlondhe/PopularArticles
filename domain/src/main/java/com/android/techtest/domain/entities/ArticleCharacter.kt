package com.android.techtest.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class ArticleCharacter(
    var results: List<ResultCharacter>
)

@Parcelize
data class ResultCharacter(
    var abstract: String,
    var byline: String,
    var id: Long,
    var media: List<MediaCharacter>,
    var publishedDate: String,
    var section: String,
    var title: String,
    var url: String,
) : Parcelable

@Parcelize
data class MediaCharacter(
    var mediaMetadata: List<MediaMetadataCharacter>
) : Parcelable

@Parcelize
data class MediaMetadataCharacter(
    var url: String,
) : Parcelable