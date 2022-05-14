package com.example.namariaanimation


import org.koin.dsl.module

val appModules = module {

    factory {
        MainViewModel(get())
    }
}