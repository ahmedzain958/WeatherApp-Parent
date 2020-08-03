package com.parents.weatherapp.di

import androidx.room.Room
import com.parents.weatherapp.data.source.local.database.DatabaseConstants.WEATHER_DATABASE
import com.parents.weatherapp.data.source.local.database.WeatherDatabase
import org.koin.dsl.module

/**
 * Created by Ahmed Zain on 6/26/2020.
 */
val localModule = module {
    single {
        Room.databaseBuilder(get(), WeatherDatabase::class.java, WEATHER_DATABASE).build()
    }
    single {
        get<WeatherDatabase>().weatherDao()
    }
}