package com.rifqi.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameResponse(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("rating")
    val rating: Any,

    @field:SerializedName("slug")
    val slug: String,

    @field:SerializedName("released")
    val released: String,

    @field:SerializedName("background_image")
    val backgroundImage: String,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("name")
    val name: String,
)