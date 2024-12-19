package com.rifqi.core.domain.repository

import com.rifqi.core.data.Resource
import com.rifqi.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface IGameRepository {

    fun getAllGame(): Flow<Resource<List<Game>>>

    fun getGameDetail(gameId: Int): Flow<Resource<Game>>

    fun getFavoriteGame(): Flow<List<Game>>

    fun setFavoriteGame(game: Game, state: Boolean)
}