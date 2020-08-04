package com.parents.weatherapp.data.source.remote

import com.parents.weatherapp.domain.model.fivedayforecast.FiveDayForecast

/**
 * Created by Ahmed Zain on 6/24/2020.
 */
class RemoteDataSourceImp(
    private val parentAppsApi: ParentAppsApi
) : RemoteDataSource {
    override suspend fun getFiveDayForecast(lat: Double, lon: Double): FiveDayForecast {
        return parentAppsApi.getFiveDayForecast(lat = lat, lon = lon)
    }
}