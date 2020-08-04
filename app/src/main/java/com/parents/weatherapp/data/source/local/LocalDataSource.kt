package com.parents.weatherapp.data.source.local

import com.parents.weatherapp.domain.model.City
import com.parents.weatherapp.domain.model.fivedayforecast.FiveDayForecast

/**
 * Created by Ahmed Zain on 6/24/2020.
 */
interface LocalDataSource {
    suspend fun cacheCity(city: City)
    suspend fun countCities(): Int
    suspend fun deleteCity(city: City)
    suspend fun getCities(): List<City>
    suspend fun saveFiveDayForecast(fiveDayForecast: FiveDayForecast, lat: Double, lon: Double)
}