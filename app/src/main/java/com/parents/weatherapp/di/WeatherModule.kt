package com.parents.weatherapp.di

import com.parents.weatherapp.data.repository.WeatherRepositoryImpl
import com.parents.weatherapp.data.source.remote.exception.ApiErrorHandle
import com.parents.weatherapp.domain.repository.WeatherRepository
import com.parents.weatherapp.domain.usecase.cities.SaveCurrentLocationCityUseCase
import com.parents.weatherapp.domain.usecase.fivedayforecast.GetFiveDayForecastUseCase
import com.parents.weatherapp.presentation.cities.CitiesViewModel
import com.parents.weatherapp.presentation.fivedayforecast.FiveDayForecastViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Ahmed Zain on 6/24/2020.
 */
@ExperimentalCoroutinesApi
val weatherModule = module {
    factory<WeatherRepository> { WeatherRepositoryImpl(get(), get()) }
    factory { createSaveCurrentLocationCityUseCase(get(), get()) }
    factory { createGetFiveDayForecastUseCaseUseCase(get(), get()) }
    viewModel { CitiesViewModel(get()) }
    viewModel { FiveDayForecastViewModel(get()) }
}

fun createSaveCurrentLocationCityUseCase(
    weatherRepository: WeatherRepository,
    apiErrorHandle: ApiErrorHandle
): SaveCurrentLocationCityUseCase {
    return SaveCurrentLocationCityUseCase(weatherRepository, apiErrorHandle)
}
fun createGetFiveDayForecastUseCaseUseCase(
    weatherRepository: WeatherRepository,
    apiErrorHandle: ApiErrorHandle
): GetFiveDayForecastUseCase {
    return GetFiveDayForecastUseCase(weatherRepository, apiErrorHandle)
}