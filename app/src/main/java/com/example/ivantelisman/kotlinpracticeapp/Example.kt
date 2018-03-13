package com.example.ivantelisman.kotlinpracticeapp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by ivantelisman on 2/8/18.
 */

data class Example(
        @SerializedName("title") val title: String,
        @SerializedName("id") val id: Int,
        @SerializedName("body") val body: String,
        @SerializedName("albumId") val albumId: Int,
        @SerializedName("url") val url: String,
        @SerializedName("thumbnailUrl") val thumbnailUrl: String
)
