package app.erickalvz.news.di.components

import app.erickalvz.news.ui.views.article.ArticleView
import app.erickalvz.news.ui.views.home.HomeView
import app.erickalvz.news.ui.views.home.HomeViewActivity
import app.erickalvz.presentation.di.modules.ViewModelBuilderModule
import app.erickalvz.shared.di.scope.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent( modules = [ViewModelBuilderModule::class])
interface HomeComponent {


    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    fun inject(homeViewActivity: HomeViewActivity)
    fun inject(homeView: HomeView)
    fun inject(articleView: ArticleView)

}