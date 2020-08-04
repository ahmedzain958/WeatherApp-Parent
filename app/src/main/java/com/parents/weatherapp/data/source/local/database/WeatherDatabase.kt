package com.parents.weatherapp.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.parents.weatherapp.data.source.local.database.DatabaseConstants.DATABASE_VERSION
import com.parents.weatherapp.domain.model.City

@Database(
    entities = [City::class/*, FiveDayForecast::class*/],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}