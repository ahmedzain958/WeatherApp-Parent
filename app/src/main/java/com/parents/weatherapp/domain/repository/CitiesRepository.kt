package com.parents.weatherapp.domain.repository

import com.parents.weatherapp.domain.model.City

/**
 * Created by Ahmed Zain on 8/3/2020.
 */
interface CitiesRepository {
    suspend fun saveCity(city: City): List<City>
}