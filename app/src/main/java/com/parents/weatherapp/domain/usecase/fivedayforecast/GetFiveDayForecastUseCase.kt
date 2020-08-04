package com.parents.weatherapp.domain.usecase.fivedayforecast

import com.parents.weatherapp.data.source.remote.exception.ApiErrorHandle
import com.parents.weatherapp.domain.model.City
import com.parents.weatherapp.domain.model.fivedayforecast.FiveDayForecast
import com.parents.weatherapp.domain.repository.WeatherRepository
import com.parents.weatherapp.domain.usecase.base.UseCase

class GetFiveDayForecastUseCase constructor(
    private val weatherRepository: WeatherRepository,
    apiErrorHandle: ApiErrorHandle
) : UseCase<FiveDayForecast, GetFiveDayForecastUseCase.Params?>(apiErrorHandle) {
    override suspend fun run(params: Params?): FiveDayForecast {
        return weatherRepository.getFiveDayForecast(
            params?.lat ?: City().coord!!.lat,
            params?.lon ?: City().coord!!.lon
        )
    }

    data class Params(val lat: Double, val lon: Double)
}