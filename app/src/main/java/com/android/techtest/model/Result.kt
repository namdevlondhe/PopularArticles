package com.android.techtest.model

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("abstract")
    var `abstract`: String,
    @SerializedName("adx_keywords")
    var adxKeywords: String,
    @SerializedName("asset_id")
    var assetId: Long,
    @SerializedName("byline")
    var byline: String,
    @SerializedName("column")
    var column: Any,
    @SerializedName("des_facet")
    var desFacet: List<String>,
    @SerializedName("eta_id")
    var etaId: Int,
    @SerializedName("geo_facet")
    var geoFacet: List<String>,
    @SerializedName("id")
    var id: Long,
    @SerializedName("media")
    var media: List<Media>,
    @SerializedName("nytdsection")
    var nytdsection: String,
    @SerializedName("org_facet")
    var orgFacet: List<String>,
    @SerializedName("per_facet")
    var perFacet: List<String>,
    @SerializedName("published_date")
    var publishedDate: String,
    @SerializedName("section")
    var section: String,
    @SerializedName("source")
    var source: String,
    @SerializedName("subsection")
    var subsection: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("type")
    var type: String,
    @SerializedName("updated")
    var updated: String,
    @SerializedName("uri")
    var uri: String,
    @SerializedName("url")
    var url: String
)