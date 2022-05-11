package com.example.namariaanimation.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentWeather (
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Long,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timezone: Long,
    val id: Long,
    val name: String,
    val cod: Long
)

@JsonClass(generateAdapter = true)
data class Clouds (
    val all: Long
)

@JsonClass(generateAdapter = true)
data class Coord (
    val lon: Double,
    val lat: Double
)

@JsonClass(generateAdapter = true)
data class Main (
    val temp: Double,

    @Json(name = "feels_like")
    val feelsLike: Double,

    @Json(name = "temp_min")
    val tempMin: Double,

    @Json(name = "temp_max")
    val tempMax: Double,

    val pressure: Long,
    val humidity: Long
)

@JsonClass(generateAdapter = true)
data class Sys (
    val type: Long,
    val id: Long,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)

@JsonClass(generateAdapter = true)
data class Weather (
    val id: Long,
    val main: String,
    val description: String,
    val icon: String
)

@JsonClass(generateAdapter = true)
data class Wind (
    val speed: Double,
    val deg: Long
)