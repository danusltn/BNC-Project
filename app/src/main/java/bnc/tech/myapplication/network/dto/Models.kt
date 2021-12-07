package bnc.tech.myapplication.network.dto

import com.google.gson.annotations.SerializedName

sealed class Models {

    data class MovieResponse(@SerializedName("id") val id: Int,
                             @SerializedName("title") val title: String = "",
                             @SerializedName("year") val year: Int,
                             @SerializedName("rating") val rating: Int,
                             @SerializedName("imageUrl") val imageUrl: String = "")

    data class MovieDetailResponse(@SerializedName("id") val id: String ="",
                                   @SerializedName("title") val title: String = "",
                                   @SerializedName("year") val year: Int,
                                   @SerializedName("rating") val rating: Int,
                                   @SerializedName("imageUrl") val imageUrl: String = "",
                                   @SerializedName("imageLargeUrl") val imageLargeUrl: String = "",
                                   @SerializedName("starring") val starring: ArrayList<String>,
                                   @SerializedName("desc") val desc: String = "",
                                   @SerializedName("releaseDate") val releaseDate: String = "",
                                   @SerializedName("duration") val duration: String = "",
                                   @SerializedName("genre") val genre: String = "")
}
