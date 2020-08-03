package com.parents.weatherapp.domain.usecase.cities

import com.parents.weatherapp.data.source.remote.exception.ApiErrorHandle
import com.parents.weatherapp.domain.model.City
import com.parents.weatherapp.domain.repository.CitiesRepository
import com.parents.weatherapp.domain.usecase.base.UseCase

class SaveCityUseCase constructor(
    private val citiesRepository: CitiesRepository,
    apiErrorHandle: ApiErrorHandle
) : UseCase<List<City>, SaveCityUseCase.Params?>(apiErrorHandle) {
    override suspend fun run(params: Params?): List<City> {
        return citiesRepository.saveCity(params?.city ?: City())
    }

    data class Params(val city: City)
}