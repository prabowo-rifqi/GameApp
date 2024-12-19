package com.rifqi.core.domain.usecase

import com.rifqi.core.domain.model.Game
import com.rifqi.core.domain.repository.IGameRepository

class GameInteractor(private val gameRepository: IGameRepository) : GameUseCase {

    override fun getAllGame() = gameRepository.getAllGame()

    override fun getGameDetail(gameId: Int) = gameRepository.getGameDetail(gameId)

    override fun getFavoriteGame() = gameRepository.getFavoriteGame()

    override fun setFavoriteGame(game: Game, state: Boolean) =
        gameRepository.setFavoriteGame(game, state)
}