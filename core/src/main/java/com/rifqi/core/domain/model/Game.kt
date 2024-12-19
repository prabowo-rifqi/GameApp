package com.rifqi.core.domain.model

data class Game(
    val gameId: Int,
    val name: String,
    val description: String,
    val rating: String,
    val slug: String? = null,
    val releaseDate: String,
    val imageUrl: String,
    val isFavorite: Boolean
)