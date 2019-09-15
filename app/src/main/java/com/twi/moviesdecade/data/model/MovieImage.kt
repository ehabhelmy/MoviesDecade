package com.twi.moviesdecade.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MovieImage(
    @SerializedName("id") val id: String?,
    @SerializedName("owner") val owner: String?,
    @SerializedName("secret") val secret: String?,
    @SerializedName("server") val server: String?,
    @SerializedName("farm") val farm: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("ispublic") val isPublic: Int?,
    @SerializedName("isfriend") val isFriend: Int?,
    @SerializedName("isfamily") val isFamily: Int?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(owner)
        parcel.writeString(secret)
        parcel.writeString(server)
        parcel.writeValue(farm)
        parcel.writeString(title)
        parcel.writeValue(isPublic)
        parcel.writeValue(isFriend)
        parcel.writeValue(isFamily)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieImage> {
        override fun createFromParcel(parcel: Parcel): MovieImage {
            return MovieImage(parcel)
        }

        override fun newArray(size: Int): Array<MovieImage?> {
            return arrayOfNulls(size)
        }
    }
}