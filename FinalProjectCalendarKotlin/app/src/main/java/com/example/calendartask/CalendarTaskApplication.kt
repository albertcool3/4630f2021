package com.example.calendartask

import android.app.Application
import com.example.calendartask.dependencyinjection.DependencyInjector

/**
 * Application class responsible for initializing the dependency injector.
 */
class CalendarTaskApplication : Application() {

    lateinit var dependencyInjector: DependencyInjector

    override fun onCreate() {
        super.onCreate()
        initDependencyInjector()
    }

    private fun initDependencyInjector() {
        dependencyInjector = DependencyInjector(this)
    }
}
