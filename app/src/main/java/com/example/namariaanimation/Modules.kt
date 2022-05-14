package com.example.namariaanimation


import com.example.namariaanimation.ui.MainViewModel
import org.koin.dsl.module

val appModules = module {

    factory {
        MainViewModel(get())
    }
}