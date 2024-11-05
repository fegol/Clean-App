package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.data.db.MyDB
import com.example.data.network.Api
import com.example.data.repository.CacheRepositoryImpl
import com.example.data.repository.ListRepositoryImpl
import com.example.data.repository.DbLocalStorageRepository
import com.example.data.repository.NetworkRepository
import com.example.domain.data.entity.ListElement
import com.example.domain.data.repository.CacheRepository
import com.example.domain.data.repository.ListRepository
import com.example.domain.data.repository.LocalStorageRepository
import com.example.domain.entity.ListElementEntity
import com.example.domain.mapper.ListElementMapper
import com.example.domain.mapper.Mapper
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
    single<MyDB> {
        Room.databaseBuilder(
            context = get(),
            klass = MyDB::class.java,
            name = "MyDb"
        ).build()
    }
    single { get<MyDB>().getElementsDao() }
    single<LocalStorageRepository> { DbLocalStorageRepository(get()) }
    single<CacheRepository> { CacheRepositoryImpl() }
    single<ListRepository> { ListRepositoryImpl() }
    single { ListUseCase(get(), get()) }
    single { ElementByIdUseCase(get(), get(), get()) }
    single<Mapper<ListElement, ListElementEntity>> { ListElementMapper(get()) }
    viewModel { MainViewModel(get(), get(), get()) }
    viewModel { DetailsViewModel(get(), get(), get()) }
}