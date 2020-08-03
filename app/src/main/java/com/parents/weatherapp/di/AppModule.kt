package com.parents.weatherapp.di

import com.parents.weatherapp.data.AppResources
import com.parents.weatherapp.data.repository.ResourcesRepository
import com.parents.weatherapp.data.source.local.LocalDataSource
import com.parents.weatherapp.data.source.local.LocalDataSourceImp
import com.parents.weatherapp.data.source.remote.RemoteDataSource
import com.parents.weatherapp.data.source.remote.RemoteDataSourceImp
import org.koin.dsl.module

/**
 * Created by Ahmed Zain on 6/24/2020.
 */


val appModule = module {
    single { AppResources(get()) }
    single { ResourcesRepository(get()) }
    single<RemoteDataSource> { RemoteDataSourceImp(get()) }
    single<LocalDataSource> { LocalDataSourceImp(get()) }
}
