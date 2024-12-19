package com.rifqi.core.data

import com.rifqi.core.data.source.local.LocalDataSource
import com.rifqi.core.data.source.remote.RemoteDataSource
import com.rifqi.core.data.source.remote.network.ApiResponse
import com.rifqi.core.data.source.remote.response.GameResponse
import com.rifqi.core.domain.model.Game
import com.rifqi.core.domain.repository.IGameRepository
import com.rifqi.core.utils.AppExecutors
import com.rifqi.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IGameRepository {

    override fun getAllGame(): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<GameResponse>>() {
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.getAllGame().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Game>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> =
                remoteDataSource.getAllGame()

            override suspend fun saveCallResult(data: List<GameResponse>) {
                val gameList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertGame(gameList)
            }
        }.asFlow()

    override fun getGameDetail(gameId: Int): Flow<Resource<Game>> {
        return remoteDataSource.getGameDetail(gameId).map { response ->
            when (response) {
                is ApiResponse.Success -> {
                    val mappedData = DataMapper.mapDetailResponseToDomain(response.data)
                    Resource.Success(mappedData)
                }

                is ApiResponse.Error -> {
                    Resource.Error(response.errorMessage)
                }

                else -> {
                    Resource.Error("Unexpected error occurred")
                }
            }
        }
    }

    override fun getFavoriteGame(): Flow<List<Game>> {
        return localDataSource.getFavoriteGame().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteGame(game: Game, state: Boolean) {
        val gameEntity = DataMapper.mapDomainToEntity(game)
        appExecutors.diskIO().execute { localDataSource.setFavoriteGame(gameEntity, state) }
    }
}

