package com.android.techtest.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class ArticleData(
    val results: List<ResultData>
)

@Parcelize
data class ResultData(
    val abstract: String,
    val byline: String,
    val id: Long,
    val media: List<MediaData>,
    val publishedDate: String,
    val section: String,
    val title: String,
    val url: String,
) : Parcelable

@Parcelize
data class MediaData(
    val mediaMetadata: List<MediaMetadataData>
) : Parcelable

@Parcelize
data class MediaMetadataData(
    val url: String,
) : Parcelable