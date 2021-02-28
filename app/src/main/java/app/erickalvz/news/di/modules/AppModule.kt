package app.erickalvz.news.di.modules

import android.content.Context
import app.erickalvz.news.R
import app.erickalvz.shared.rx.AppSchedulerProvider
import app.erickalvz.shared.rx.SchedulerProvider

import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton


@Module
class AppModule {


    @Singleton
    @Provides
    fun provideApiURL(context: Context): String {
        return context.getString(R.string.apiURL)
    }


    @Singleton
    @Provides
    @Named("apiKEY")
    fun provideApiKey(context: Context): String {
        return context.getString(R.string.apiKey)
    }

    @Singleton
    @Provides
    fun provideSchedulers(): SchedulerProvider {
        return AppSchedulerProvider()
    }


}