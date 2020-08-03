package com.parents.weatherapp.data.repository

import com.parents.weatherapp.data.source.local.LocalDataSource
import com.parents.weatherapp.data.source.remote.RemoteDataSource
import com.parents.weatherapp.domain.model.City
import com.parents.weatherapp.domain.repository.CitiesRepository

/**
 * Created by Ahmed Zain on 8/3/2020.
 */
class CitiesRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : CitiesRepository {
    private val TAG = CitiesRepositoryImpl::class.java.name
    override suspend fun saveCity(city: City): List<City> {
        localDataSource.cacheCity(city)
        return localDataSource.getCities()
    }
}