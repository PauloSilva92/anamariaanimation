package com.example.namariaanimation

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout


class Namaria @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val leftTranslation = 500.toFloat()
    private val rightTranslation = (-500).toFloat()
    private val animationDuration = 1200.toLong()
    private val animationInterval = 3000.toLong()

    private val hourText: AppCompatTextView by viewProvider(R.id.hour_text)
    private val weatherImage: AppCompatImageView by viewProvider(R.id.weather_image)
    private val temperatureText: AppCompatTextView by viewProvider(R.id.temperature_text)
    private val locationText: AppCompatTextView by viewProvider(R.id.location_text)

    init {
        inflate(context, R.layout.namaria_layout, this)
        hourText.translationX = leftTranslation
        setupHourTimer()
    }

    fun setHour(hour: String) {
        hourText.text = hour
    }

    fun setTemperature(temperature: String) {
        temperatureText.text = temperature
    }

    fun setLocationName(location: String) {
        locationText.text = location
    }

    fun setWeatherIcon(@DrawableRes weatherIcon: Int) {
        weatherImage.setImageResource(weatherIcon)
    }

    private fun setupHourTimer() {
        postDelayed({

            weatherImage.animateTranslationX(rightTranslation)
            temperatureText.animateTranslationX(rightTranslation)

            hourText.translationX = leftTranslation
            hourText.animateTranslationX(0f)

            setupWeatherTimer()
        }, animationInterval)
    }

    private fun setupWeatherTimer() {
        postDelayed({

            weatherImage.translationX = leftTranslation
            temperatureText.translationX = leftTranslation

            weatherImage.animateTranslationX(0f)
            temperatureText.animateTranslationX(0f)

            hourText.animateTranslationX(rightTranslation)

            setupHourTimer()
        }, animationInterval)
    }


    private fun View.animateTranslationX(value: Float) {
        animate().translationX(value).apply {
            duration = animationDuration
        }.start()
    }
}