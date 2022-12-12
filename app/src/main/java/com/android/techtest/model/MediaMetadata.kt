package com.android.techtest.model

import com.google.gson.annotations.SerializedName

data class MediaMetadata(
    @SerializedName("format")
    var format: String,
    @SerializedName("height")
    var height: Int,
    @SerializedName("url")
    var url: String,
    @SerializedName("width")
    var width: Int
)