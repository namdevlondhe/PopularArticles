package com.android.techtest.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class ArticleData(
    var results: List<ResultData>
)

@Parcelize
data class ResultData(
    var abstract: String,
    var byline: String,
    var id: Long,
    var media: List<MediaData>,
    var publishedDate: String,
    var section: String,
    var title: String,
    var url: String,
) : Parcelable

@Parcelize
data class MediaData(
    var mediaMetadata: List<MediaMetadataData>
) : Parcelable

@Parcelize
data class MediaMetadataData(
    var url: String,
) : Parcelable