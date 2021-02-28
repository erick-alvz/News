package app.erickalvz.data.remote.services

import app.erickalvz.data.remote.models.ArticleDto
import app.erickalvz.data.remote.models.CollectionDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface IApiService {

    @GET("everything")
    fun fetchArticles(
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 1,
        @Query("q") q: String = "IGN",
        @Query("sortBy") sortBy: String = "publishedAt",
    ) : Single<CollectionDto<ArticleDto>>


}