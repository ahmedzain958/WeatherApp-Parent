package com.parents.weatherapp.data.source.local

import com.parents.weatherapp.data.source.local.database.WeatherDao
import com.parents.weatherapp.domain.model.City


/**
 * Created by Ahmed Zain on 6/24/2020.
 */
class LocalDataSourceImp(
    private val weatherDao: WeatherDao
) : LocalDataSource {
    override suspend fun cacheCity(city: City) {
        weatherDao.insert(city)
    }

    override suspend fun countCities(): Int {
        return weatherDao.countCities()
    }

    override suspend fun deleteCity(city: City) {
        weatherDao.deleteCity(city)
    }

    override suspend fun getCities(): List<City> {
        return weatherDao.getCities()
    }

}