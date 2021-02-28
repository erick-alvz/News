package app.erickalvz.news.di.modules

import app.erickalvz.news.di.components.HomeComponent
import dagger.Module

@Module(
    subcomponents = [
        HomeComponent::class
    ]
)
class SubComponentsModule {}