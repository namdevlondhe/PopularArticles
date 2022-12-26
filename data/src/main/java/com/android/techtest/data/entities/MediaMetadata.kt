package com.android.techtest.data.entities

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MediaMetadata(
    @SerializedName("format")
    var format: String?,
    @SerializedName("height")
    var height: Int,
    @SerializedName("url")
    var url: String?,
    @SerializedName("width")
    var width: Int
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(format)
        parcel.writeInt(height)
        parcel.writeString(url)
        parcel.writeInt(width)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MediaMetadata> {
        override fun createFromParcel(parcel: Parcel): MediaMetadata {
            return MediaMetadata(parcel)
        }

        override fun newArray(size: Int): Array<MediaMetadata?> {
            return arrayOfNulls(size)
        }
    }
}