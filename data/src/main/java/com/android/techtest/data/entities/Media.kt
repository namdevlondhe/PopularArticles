package com.android.techtest.data.entities

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class Media(
    @SerializedName("approved_for_syndication")
    var approvedForSyndication: Int,
    @SerializedName("caption")
    var caption: String?,
    @SerializedName("copyright")
    var copyright: String?,
    @SerializedName("media-metadata")
    var mediaMetadata: ArrayList<MediaMetadata>?,
    @SerializedName("subtype")
    var subtype: String?,
    @SerializedName("type")
    var type: String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.createTypedArrayList(MediaMetadata),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(approvedForSyndication)
        parcel.writeString(caption)
        parcel.writeString(copyright)
        parcel.writeTypedList(mediaMetadata)
        parcel.writeString(subtype)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Media> {
        override fun createFromParcel(parcel: Parcel): Media {
            return Media(parcel)
        }

        override fun newArray(size: Int): Array<Media?> {
            return arrayOfNulls(size)
        }
    }
}