package com.android.techtest.data.entities

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("abstract")
    var abstract: String?,
    @SerializedName("adx_keywords")
    var adxKeywords: String?,
    @SerializedName("asset_id")
    var assetId: Long?,
    @SerializedName("byline")
    var byline: String?,
    @SerializedName("column")
    var column: Any?,
    @SerializedName("des_facet")
    var desFacet: List<String>?,
    @SerializedName("eta_id")
    var etaId: Int?,
    @SerializedName("geo_facet")
    var geoFacet: List<String>?,
    @SerializedName("id")
    var id: Long?,
    @SerializedName("media")
    var media: List<Media>?,
    @SerializedName("nytdsection")
    var nytdsection: String?,
    @SerializedName("org_facet")
    var orgFacet: List<String>?,
    @SerializedName("per_facet")
    var perFacet: List<String>?,
    @SerializedName("published_date")
    var publishedDate: String?,
    @SerializedName("section")
    var section: String?,
    @SerializedName("source")
    var source: String?,
    @SerializedName("subsection")
    var subsection: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("type")
    var type: String?,
    @SerializedName("updated")
    var updated: String?,
    @SerializedName("uri")
    var uri: String?,
    @SerializedName("url")
    var url: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readString(),
        null,
        parcel.createStringArrayList(),
        parcel.readInt(),
        parcel.createStringArrayList(),
        parcel.readLong(),
        parcel.createTypedArrayList(Media),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(abstract)
        parcel.writeString(adxKeywords)
        assetId?.let { parcel.writeLong(it) }
        parcel.writeString(byline)
        parcel.writeStringList(desFacet)
        etaId?.let { parcel.writeInt(it) }
        parcel.writeStringList(geoFacet)
        id?.let { parcel.writeLong(it) }
        parcel.writeTypedList(media)
        parcel.writeString(nytdsection)
        parcel.writeStringList(orgFacet)
        parcel.writeStringList(perFacet)
        parcel.writeString(publishedDate)
        parcel.writeString(section)
        parcel.writeString(source)
        parcel.writeString(subsection)
        parcel.writeString(title)
        parcel.writeString(type)
        parcel.writeString(updated)
        parcel.writeString(uri)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Result> {
        override fun createFromParcel(parcel: Parcel): Result {
            return Result(parcel)
        }

        override fun newArray(size: Int): Array<Result?> {
            return arrayOfNulls(size)
        }
    }
}