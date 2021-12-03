package com.example.tapp.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SitioItem(
    @SerializedName("author_photo")
    val authorPhoto: String,
    @SerializedName("description_img")
    val descriptionImg: String,
    @SerializedName("distance_airport")
    val distanceAirport: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("long_description")
    val longDescription: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("score")
    val score: String,
    @SerializedName("short_description")
    val shortDescription: String,
    @SerializedName("temperature")
    val temperature: String,
    @SerializedName("urlPicture")
    val urlPicture: String
) : Serializable