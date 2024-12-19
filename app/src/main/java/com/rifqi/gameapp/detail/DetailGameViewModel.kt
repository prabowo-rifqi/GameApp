package com.rifqi.gameapp.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rifqi.core.domain.model.Game
import com.rifqi.core.domain.usecase.GameUseCase

class DetailGameViewModel(private val gameUseCase: GameUseCase) : ViewModel() {
    fun setFavoriteGame(game: Game, newStatus: Boolean) =
        gameUseCase.setFavoriteGame(game, newStatus)

    fun getDetailGame(gameId: Int) = gameUseCase.getGameDetail(gameId).asLiveData()
}

