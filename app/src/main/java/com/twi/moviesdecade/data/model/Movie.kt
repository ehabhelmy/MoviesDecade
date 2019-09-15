package com.twi.moviesdecade.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.twi.findintjobs.data.model.BaseModel

/**
 *  {
"title": ​"(500) Days of Summer"​, "year": ​2009​,
"cast": [
​"Joseph Gordon-Levitt"​,
​"Zooey Deschanel" ],
"genres": [ ​"Romance"​,
​"Comedy" ],
"rating": 4 }
 */

@Entity(tableName = "movie_table")
data class Movie(
    @SerializedName("title") var title: String?, var year: Int?,
    @SerializedName("cast") var cast: List<String>?,
    @SerializedName("genres") var genres: List<String>?,
    @SerializedName("rating") var rating: Int?
) : BaseModel(), Parcelable{

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var movieImages: List<MovieImage>? = null

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
        id = parcel.readInt()
        movieImages = parcel.createTypedArrayList(MovieImage)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeValue(year)
        parcel.writeStringList(cast)
        parcel.writeStringList(genres)
        parcel.writeValue(rating)
        parcel.writeInt(id)
        parcel.writeTypedList(movieImages)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }


}