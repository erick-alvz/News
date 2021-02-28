package app.erickalvz.news.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.erickalvz.domain.models.Article
import app.erickalvz.news.databinding.ItemViewArticleBinding
import com.squareup.picasso.Picasso

class NewsVH(private val binding: ItemViewArticleBinding) : RecyclerView.ViewHolder(binding.root) {


    fun setData(article: Article) {

        binding.title.text = article.title
        binding.source.text = article.source
        binding.description.text = article.description

        article.urlToImage?.let { Picasso.get().load(it).into(binding.image) }

    }

    fun onViewRecycle() {
        Picasso.get().cancelRequest(binding.image)
        binding.image.setImageBitmap(null)
    }

    companion object {

        @JvmStatic
        fun from(parent: ViewGroup): NewsVH {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemViewArticleBinding.inflate(layoutInflater, parent, false)
            return NewsVH(binding)
        }
    }
}