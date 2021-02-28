package app.erickalvz.presentation.di.modules

import androidx.lifecycle.ViewModel
import app.erickalvz.presentation.di.daggerkey.ViewModelKey
import app.erickalvz.presentation.viewmodel.DetailViewModel
import app.erickalvz.presentation.viewmodel.HomeViewModel
import app.erickalvz.shared.di.scope.ActivityScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBuilderModule {


    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    @ActivityScope
    internal abstract fun provideHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    @ActivityScope
    internal abstract fun provideDetailViewModel(homeViewModel: DetailViewModel): ViewModel

}