package com.example.namariaanimation

import com.example.namariaanimation.network.WeatherApi
import com.example.namariaanimation.network.WeatherRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


val appModules = module {

    factory {
        WeatherRepository(get())
    }

    factory {
        MainViewModel(get())
    }
}

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
}

fun providerWeatherApi(retrofit: Retrofit): WeatherApi = retrofit.create(WeatherApi::class.java)