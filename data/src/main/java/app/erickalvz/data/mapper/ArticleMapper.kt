package app.erickalvz.data.mapper

import app.erickalvz.data.remote.models.ArticleDto
import app.erickalvz.domain.models.Article
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Singleton

@Singleton
class ArticleMapper : Mapper<ArticleDto, Article> {


    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())


    override fun map(input: ArticleDto): Article {
        return Article(
            input.source.name,
            input.author,
            input.title ?: "",
            input.description ?: "",
            input.url ?: "",
            input.urlToImage,
            publishedAt = simpleDateFormat.parse(input.publishedAt ?: "")?.time ?: 0L
        )
    }
}