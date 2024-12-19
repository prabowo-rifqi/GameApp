package com.rifqi.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListGameResponse(

    @field:SerializedName("next")
    val next: String? = null,

    @field:SerializedName("previous")
    val previous: Any? = null,

    @field:SerializedName("count")
    val count: Int? = null,

    @field:SerializedName("results")
    val results: List<GameResponse>,
)
