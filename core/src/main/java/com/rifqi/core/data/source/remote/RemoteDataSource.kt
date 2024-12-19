package com.rifqi.core.data.source.remote

import android.util.Log
import com.rifqi.core.data.source.remote.network.ApiResponse
import com.rifqi.core.data.source.remote.network.ApiService
import com.rifqi.core.data.source.remote.response.DetailGameResponse
import com.rifqi.core.data.source.remote.response.GameResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    fun getAllGame(): Flow<ApiResponse<List<GameResponse>>> {
        return flow {
            try {
                val response = apiService.getList("466ff543031a484ea6c7c8daeecf25b4")
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getGameDetail(gameId: Int): Flow<ApiResponse<DetailGameResponse>> {
        return flow {
            try {
                val response = apiService.getDetail(gameId, "466ff543031a484ea6c7c8daeecf25b4")
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}

