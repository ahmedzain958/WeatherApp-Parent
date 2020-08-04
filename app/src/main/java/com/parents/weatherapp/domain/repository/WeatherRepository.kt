package com.parents.weatherapp.domain.repository

import com.parents.weatherapp.domain.model.City
import com.parents.weatherapp.domain.model.fivedayforecast.FiveDayForecast

/**
 * Created by Ahmed Zain on 8/3/2020.
 */
interface WeatherRepository {
    suspend fun saveCurrentLocationCity(city: City): List<City>
    suspend fun getFiveDayForecast(
        lat: Double,
        lon: Double
    ): FiveDayForecast
}