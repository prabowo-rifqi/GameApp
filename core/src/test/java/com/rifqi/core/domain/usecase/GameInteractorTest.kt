package com.rifqi.core.domain.usecase


import com.rifqi.core.data.GameRepository
import com.rifqi.core.data.Resource
import io.kotest.common.runBlocking
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameInteractorTest {
    private val mockRepository : GameRepository = mockk()
    private lateinit var gameInteractor: GameInteractor
    private val dataDummy = DataDummy.generateDummyGame()

    @BeforeEach
    fun setUp(){
        gameInteractor = GameInteractor(mockRepository)
    }

    @Test
    fun `getAllGame should return a list of games wrapped in Resource`() = runBlocking {

        val mockResource = Resource.Success(dataDummy)
        coEvery { mockRepository.getAllGame() } returns flowOf(mockResource)

        // Act
        val result = gameInteractor.getAllGame()

        // Assert
        result.collect { resource ->
            resource shouldBe mockResource
        }
    }
}