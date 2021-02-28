package app.erickalvz.data.di

import android.content.Context
import app.erickalvz.data.mapper.Mapper
import app.erickalvz.data.mapper.ArticleMapper
import app.erickalvz.data.remote.models.ArticleDto
import app.erickalvz.data.remote.services.IApiService
import app.erickalvz.data.remote.services.ISocketService
import app.erickalvz.data.repository.NewsRepositoryImpl
import app.erickalvz.data.repository.SocketRepositoryImpl
import app.erickalvz.domain.models.Article
import app.erickalvz.domain.repository.NewsRepository
import app.erickalvz.domain.repository.SocketRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RepositoryModule {


    @Singleton
    @Provides
    fun provideArticleMapper(): Mapper<ArticleDto, Article> {
        return ArticleMapper()
    }

    @Singleton
    @Provides
    fun provideArticleRepository(
        apiService: IApiService,
        mapper: Mapper<ArticleDto, Article>
    ): NewsRepository {
        return NewsRepositoryImpl(
            apiService,
            mapper
        )
    }

    @Singleton
    @Provides
    fun provideSocketRepository(
        socketService: ISocketService
    ): SocketRepository {
        return SocketRepositoryImpl(socketService)
    }
}