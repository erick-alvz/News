package app.erickalvz.news.di

import android.content.Context
import app.erickalvz.news.App
import app.erickalvz.news.di.components.AppComponent


val Context.appComponent: AppComponent
    get() = (this.applicationContext as App).appComponent