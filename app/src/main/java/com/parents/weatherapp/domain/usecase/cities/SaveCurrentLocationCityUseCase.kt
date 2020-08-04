package com.parents.weatherapp.domain.usecase.cities

import com.parents.weatherapp.data.source.remote.exception.ApiErrorHandle
import com.parents.weatherapp.domain.model.City
import com.parents.weatherapp.domain.repository.WeatherRepository
import com.parents.weatherapp.domain.usecase.base.UseCase

class SaveCurrentLocationCityUseCase constructor(
    private val weatherRepository: WeatherRepository,
    apiErrorHandle: ApiErrorHandle
) : UseCase<List<City>, SaveCurrentLocationCityUseCase.Params?>(apiErrorHandle) {
    override suspend fun run(params: Params?): List<City> {
        return weatherRepository.saveCurrentLocationCity(params?.city ?: City())
    }

    data class Params(val city: City)
}