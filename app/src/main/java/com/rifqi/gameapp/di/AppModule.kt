package com.rifqi.gameapp.di

import com.rifqi.core.domain.usecase.GameInteractor
import com.rifqi.core.domain.usecase.GameUseCase
import com.rifqi.gameapp.detail.DetailGameViewModel
import com.rifqi.gameapp.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module {
    factory<GameUseCase> { GameInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailGameViewModel(get()) }
}