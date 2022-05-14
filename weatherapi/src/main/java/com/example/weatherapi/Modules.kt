package com.example.weatherapi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModules = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor (
                HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BASIC) }
            )
            .build()
    }

    single {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .client(get())
            .build()
    }

    factory { providerWeatherApi(get()) }

    factory { WeatherRepository(get()) }
}

fun providerWeatherApi(retrofit: Retrofit): WeatherApi = retrofit.create(WeatherApi::class.java)