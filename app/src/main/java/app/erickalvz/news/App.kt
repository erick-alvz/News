package app.erickalvz.news

import android.app.Application
import app.erickalvz.news.di.components.AppComponent
import app.erickalvz.news.di.components.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory().create(this)
    }
}