package com.rifqi.core.utils

import com.rifqi.core.data.source.local.entity.GameEntity
import com.rifqi.core.data.source.remote.response.DetailGameResponse
import com.rifqi.core.data.source.remote.response.GameResponse
import com.rifqi.core.domain.model.Game

object DataMapper {
    fun mapResponsesToEntities(input: List<GameResponse>): List<GameEntity> {
        val gameList = ArrayList<GameEntity>()
        input.map {
            val game = GameEntity(
                gameId = it.id,
                description = it.description ?: "",
                name = it.name,
                rating = it.rating.toString(),
                slug = it.slug,
                releaseDate = it.released,
                imageUrl = it.backgroundImage,
                isFavorite = false,
            )
            gameList.add(game)
        }
        return gameList
    }

    fun mapDetailResponseToDomain(input: DetailGameResponse): Game {
        val game =
            input.let {
                Game(
                    gameId = it.id,
                    description = it.descriptionRaw ?: "",
                    name = it.nameOriginal ?: "",
                    rating = it.rating.toString(),
                    releaseDate = it.released ?: "",
                    imageUrl = it.backgroundImage ?: "",
                    isFavorite = false,
                )
            }
        return game
    }


    fun mapEntitiesToDomain(input: List<GameEntity>): List<Game> =
        input.map {
            Game(
                gameId = it.gameId,
                description = it.description,
                name = it.name,
                rating = it.rating,
                slug = it.slug,
                releaseDate = it.releaseDate,
                imageUrl = it.imageUrl,
                isFavorite = it.isFavorite,
            )
        }

    fun mapDomainToEntity(input: Game) = GameEntity(
        gameId = input.gameId,
        description = input.description,
        name = input.name,
        rating = input.rating,
        slug = input.slug ?: "",
        releaseDate = input.releaseDate,
        imageUrl = input.imageUrl,
        isFavorite = input.isFavorite
    )
}