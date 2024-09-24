package com.example.myapplication.di

import com.example.data.repository.ListRepositoryImpl
import com.example.domain.repository.ListRepository
import com.example.domain.usecase.ListUseCase
import com.example.myapplication.main.vm.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<ListRepository> { ListRepositoryImpl() }
    single { ListUseCase(get()) }
    viewModel { MainViewModel(get()) }
}