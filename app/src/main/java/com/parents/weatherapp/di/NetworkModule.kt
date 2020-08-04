package com.parents.weatherapp.di

import com.google.gson.GsonBuilder
import com.parents.weatherapp.BuildConfig
import com.parents.weatherapp.data.source.remote.ConnectivityInterceptor
import com.parents.weatherapp.data.source.remote.ConnectivityInterceptorImpl
import com.parents.weatherapp.data.source.remote.HeadersInterceptor
import com.parents.weatherapp.data.source.remote.ParentAppsApi
import com.parents.weatherapp.data.source.remote.exception.ApiErrorHandle
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by Ahmed Zain on 6/24/2020.
 */

val networkModule = module {
    single { HeadersInterceptor() }
    single<ConnectivityInterceptor> { ConnectivityInterceptorImpl(get()) }
    single { createOkHttpClient(get(), get()) }
    single { createRetrofit(get(), BuildConfig.BASE_URL) }
    single {
        createWeatherService(get())
    }
    single { ApiErrorHandle(get()) }
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    val gson = GsonBuilder()
        .setLenient()
        .create()
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson)).build()
}

fun createOkHttpClient(
    connectivityInterceptor: ConnectivityInterceptor,
    headersInterceptor: HeadersInterceptor
): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .addInterceptor(headersInterceptor)
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(connectivityInterceptor)
        .build()
}

fun createWeatherService(retrofit: Retrofit): ParentAppsApi {
    return retrofit.create(ParentAppsApi::class.java)
}