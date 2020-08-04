package com.parents.weatherapp.data.source.remote

import com.parents.weatherapp.domain.model.fivedayforecast.FiveDayForecast
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Ahmed Zain on 6/24/2020.
 */
interface ParentAppsApi {
    @GET("/data/2.5/forecast")
    suspend fun getFiveDayForecast(
        @Query(encoded = true,value = "appid") appid: String = "816ad9e0c3cb984afbe68550fe4f0a06",
        @Query(encoded = true,value = "lat") lat: Double,
        @Query(encoded = true,value = "lon") lon: Double,
        @Query(encoded = true,value = "units") units: String = "metric"
    ): FiveDayForecast
}