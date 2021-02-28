package app.erickalvz.data.repository

import app.erickalvz.data.mapper.Mapper
import app.erickalvz.data.remote.models.ArticleDto
import app.erickalvz.data.remote.services.IApiService
import app.erickalvz.domain.models.Article
import app.erickalvz.domain.models.PagedCollection
import app.erickalvz.domain.repository.NewsRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class NewsRepositoryImpl @Inject constructor(
    private val service: IApiService,
    private val mapper: Mapper<ArticleDto, Article>
) : NewsRepository {


    override fun fetchArticles(page: Int, pageSize: Int): Single<PagedCollection<Article>> {

        return service.fetchArticles(page, pageSize).map { collection ->
            PagedCollection(collection.articles.map { mapper.map(it) }, page, pageSize)
        }

    }
}