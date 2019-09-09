package com.twi.moviesdecade.data.model

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
) : BaseModel() {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var imageUrl: String? = null
}