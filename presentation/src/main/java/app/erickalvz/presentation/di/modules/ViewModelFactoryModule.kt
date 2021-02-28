package app.erickalvz.presentation.di.modules

import androidx.lifecycle.ViewModelProvider
import app.erickalvz.presentation.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module


@Module
abstract class ViewModelFactoryModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}