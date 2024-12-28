package com.rifqi.core.domain.usecase

import com.rifqi.core.domain.model.Game

object DataDummy {
    fun generateDummyGame(): List<Game> {
        val gameList = ArrayList<Game>()
        for (i in 1..10) {
            val game = Game(
                gameId = i,
                name = "Game $i",
                releaseDate = "2021-09-01",
                rating = "4.5",
                imageUrl = "https://via.placeholder.com/600/92c952",
                isFavorite = false,
                description = "lorem ipsum dolor sit amet",
                slug = "game-$i"
            )
            gameList.add(game)
        }
        return gameList
    }
}