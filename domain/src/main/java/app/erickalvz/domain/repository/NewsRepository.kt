package app.erickalvz.domain.repository

import app.erickalvz.domain.models.Article
import app.erickalvz.domain.models.PagedCollection
import io.reactivex.Single

interface NewsRepository {

    fun fetchArticles(page: Int, pageSize: Int) : Single<PagedCollection<Article>>
}