package com.example.myapplication.di

import com.example.data.network.Api
import com.example.data.repository.NetworkRepository
import com.example.domain.repository.ListRepository
import com.example.domain.usecase.ListUseCase
import com.example.myapplication.details.vm.DetailsViewModel
import com.example.myapplication.main.vm.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://mocki.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(Api::class.java)
    }
    single<ListRepository> { NetworkRepository(get()) }
    single { ListUseCase(get()) }
    viewModel { MainViewModel(get(), get()) }
    viewModel { DetailsViewModel(get()) }
}