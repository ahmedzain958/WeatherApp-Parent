package com.parents.weatherapp.data.source.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.parents.weatherapp.data.source.local.database.DatabaseConstants.TABLE_CITY
import com.parents.weatherapp.domain.model.City

@Dao
interface WeatherDao {
    @Insert
    suspend fun insert(city: City)

    @Delete
    suspend fun deleteCity(city: City)

    @Query("SELECT COUNT(*) FROM $TABLE_CITY")
    suspend fun countCities(): Int

    @Query("SELECT * FROM $TABLE_CITY")
    suspend fun getCities(): List<City>

    @Insert
    suspend fun insertAll(city: City)
}