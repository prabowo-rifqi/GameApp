package com.rifqi.gameapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rifqi.core.domain.usecase.GameUseCase

class HomeViewModel(gameUseCase: GameUseCase) : ViewModel() {
    val game = gameUseCase.getAllGame().asLiveData()
}