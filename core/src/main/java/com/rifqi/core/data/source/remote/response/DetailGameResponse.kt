package com.rifqi.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailGameResponse(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name_original")
    val nameOriginal: String? = null,

    @field:SerializedName("rating")
    val rating: Any? = null,

    @field:SerializedName("released")
    val released: String? = null,

    @field:SerializedName("description_raw")
    val descriptionRaw: String? = null,

    @field:SerializedName("background_image")
    val backgroundImage: String? = null,
)