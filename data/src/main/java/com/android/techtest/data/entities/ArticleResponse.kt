package com.android.techtest.data.entities

import com.google.gson.annotations.SerializedName


class ArticleResponse(
    @SerializedName("copyright")
    var copyright: String,
    @SerializedName("num_results")
    var numResults: Int,
    @SerializedName("results")
    var results: List<Result>,
    @SerializedName("status")
    var status: String
)