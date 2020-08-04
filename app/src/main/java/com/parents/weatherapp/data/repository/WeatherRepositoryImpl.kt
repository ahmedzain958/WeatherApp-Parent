package com.parents.weatherapp.data.repository

import com.parents.weatherapp.data.source.local.LocalDataSource
import com.parents.weatherapp.data.source.remote.RemoteDataSource
import com.parents.weatherapp.domain.model.City
import com.parents.weatherapp.domain.model.fivedayforecast.FiveDayForecast
import com.parents.weatherapp.domain.repository.WeatherRepository

/**
 * Created by Ahmed Zain on 8/3/2020.
 */
class WeatherRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : WeatherRepository {
    private val TAG = WeatherRepositoryImpl::class.java.name
    override suspend fun saveCurrentLocationCity(city: City): List<City> {
        val cities = localDataSource.getCities()
        if (cities.size == 1)
            return cities
        localDataSource.cacheCity(city)
        return localDataSource.getCities()
    }

    override suspend fun getFiveDayForecast(lat: Double, lon: Double): FiveDayForecast {
        val fiveDayForecast = remoteDataSource.getFiveDayForecast(lat, lon)
        //todo
        localDataSource.saveFiveDayForecast(fiveDayForecast, lat, lon)
        return fiveDayForecast
    }
}