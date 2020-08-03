package com.parents.weatherapp.di

import com.parents.weatherapp.data.repository.CitiesRepositoryImpl
import com.parents.weatherapp.data.source.remote.exception.ApiErrorHandle
import com.parents.weatherapp.domain.repository.CitiesRepository
import com.parents.weatherapp.domain.usecase.cities.SaveCityUseCase
import com.parents.weatherapp.presentation.cities.CitiesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Ahmed Zain on 6/24/2020.
 */
@ExperimentalCoroutinesApi
val weatherModule = module {
    factory<CitiesRepository> { CitiesRepositoryImpl(get(), get()) }
    factory { createSaveCityUseCase(get(), get()) }
    viewModel { CitiesViewModel(get()) }
}

fun createSaveCityUseCase(
    citiesRepository: CitiesRepository,
    apiErrorHandle: ApiErrorHandle
): SaveCityUseCase {
    return SaveCityUseCase(citiesRepository, apiErrorHandle)
}