package com.example.myapplication.di

import android.content.Context
import com.example.data.network.Api
import com.example.data.repository.CacheRepositoryImpl
import com.example.data.repository.LocalStorageRepositoryImpl
import com.example.data.repository.NetworkRepository
import com.example.domain.repository.CacheRepository
import com.example.domain.repository.ListRepository
import com.example.domain.repository.LocalStorageRepository
import com.example.domain.usecase.ElementByIdUseCase
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
    single {
        get<Context>().getSharedPreferences("prefs", Context.MODE_PRIVATE)
    }
    single<LocalStorageRepository> { LocalStorageRepositoryImpl(get()) }
    single<CacheRepository> { CacheRepositoryImpl() }
    single<ListRepository> { NetworkRepository(get(), get()) }
    single { ListUseCase(get()) }
    single { ElementByIdUseCase(get(), get()) }
    viewModel { MainViewModel(get(), get()) }
    viewModel { DetailsViewModel(get(), get(), get()) }
}