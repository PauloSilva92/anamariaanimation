package com.example.namariaanimation

import kotlin.math.roundToInt

fun Double.toTemperatureDegrees(): String = this.roundToInt().toString() + "°C"