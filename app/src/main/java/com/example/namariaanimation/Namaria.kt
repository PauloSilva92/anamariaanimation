package com.example.namariaanimation

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group


class Namaria @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val leftTranslation = (-500).toFloat()
    private val rightTranslation = 500.toFloat()
    private val animationDuration = 1200.toLong()
    private val animationInterval = 3000.toLong()

    private val hourText: TextView by viewProvider(R.id.hour_text)
    private val weatherImage: AppCompatImageView by viewProvider(R.id.weather_image)
    private val temperatureText: AppCompatTextView by viewProvider(R.id.temperature_text)

    init {
        inflate(context, R.layout.namaria_layout, this)
        setupHourTimer()
    }

    private fun setupHourTimer() {
        postDelayed({
            hourText.visibility = View.VISIBLE

            weatherImage.animateTranslationX(rightTranslation)
            temperatureText.animateTranslationX(rightTranslation)

            hourText.translationX = leftTranslation
            hourText.animateTranslationX(0f)
            setupWeatherTimer()

        }, animationInterval)
    }

    private fun setupWeatherTimer() {
        postDelayed({
            hourText.visibility = View.VISIBLE
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