package com.example.namariaanimation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.namariaanimation.network.CurrentWeather
import com.example.namariaanimation.network.WeatherRepository
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val mainViewModel: MainViewModel by viewModel()
    private val namaria: Namaria by viewProvider(R.id.namaria)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.getCurrentWeather().observe(this) { currentWeather ->
            namaria.setLocationName(currentWeather.name)
            namaria.setTemperature(currentWeather.main.temp.toTemperatureDegrees())
        }
    }
}


class MainViewModel(private val weatherRepository: WeatherRepository): ViewModel() {

    fun getCurrentWeather(): LiveData<CurrentWeather> = liveData {
        emit(weatherRepository.getCurrentWeather())
    }

}