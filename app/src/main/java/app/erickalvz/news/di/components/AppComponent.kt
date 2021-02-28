package app.erickalvz.news.di.components

import android.content.Context
import app.erickalvz.data.di.ApiServiceModule
import app.erickalvz.data.di.NetworkModule
import app.erickalvz.data.di.RepositoryModule

import app.erickalvz.news.di.modules.AppModule
import app.erickalvz.news.di.modules.SubComponentsModule
import app.erickalvz.news.ui.views.article.ArticleView

import app.erickalvz.presentation.di.modules.ViewModelBuilderModule
import app.erickalvz.presentation.di.modules.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        ApiServiceModule::class,
        RepositoryModule::class,
        ViewModelFactoryModule::class,
        SubComponentsModule::class]
)
interface AppComponent {

    fun buildActivityComponent(): HomeComponent.Factory


    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

}