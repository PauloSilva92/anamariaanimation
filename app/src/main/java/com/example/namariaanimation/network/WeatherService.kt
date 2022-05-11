package com.example.namariaanimation.network

import retrofit2.http.GET


interface WeatherApi {

    @GET("weather?q=porto%20alegre&appid=65c9c0f671718ba5fd286945a7b3c273&lang=pt_br&units=metric")
    suspend fun getCurrentWeather(): CurrentWeather

}

class WeatherRepository (private val weatherApi: WeatherApi) {

    suspend fun getCurrentWeather(): CurrentWeather {
        return weatherApi.getCurrentWeather()
    }

}