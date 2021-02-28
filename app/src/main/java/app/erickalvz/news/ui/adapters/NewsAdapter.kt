package app.erickalvz.news.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import app.erickalvz.domain.models.Article

class NewsAdapter() : Adapter<NewsVH>() {


    private var items: SortedList<Article> = SortedList(Article::class.java,
        SortedList.BatchedCallback(object : SortedListAdapterCallback<Article>(this) {

            override fun areItemsTheSame(item1: Article, item2: Article): Boolean =
                item1.title == item2.title

            override fun compare(oldItem: Article, newItem: Article): Int =
                -oldItem.publishedAt.compareTo(newItem.publishedAt)

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem.title == newItem.title


            override fun onInserted(position: Int, count: Int) {
                notifyItemRangeInserted(position, count)
            }

            override fun onRemoved(position: Int, count: Int) {
                notifyItemRangeRemoved(position, count)
            }

            override fun onMoved(fromPosition: Int, toPosition: Int) {
                notifyItemMoved(fromPosition, toPosition)
            }

            override fun onChanged(position: Int, count: Int) {
                notifyItemRangeChanged(position, count)
            }

        })
    )

    private var listener: NewsAdapterListener? = null

    fun addListener(listener: NewsAdapterListener) {
        this.listener = listener
    }


    fun addItems(items: List<Article>) {
        this.items.beginBatchedUpdates()
        this.items.addAll(items)
        this.items.endBatchedUpdates()
    }

    fun replace(items: List<Article>) {
        this.items.beginBatchedUpdates()
        this.items.replaceAll(items)
        this.items.endBatchedUpdates()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsVH {
        return NewsVH.from(parent)
    }


    override fun onBindViewHolder(holder: NewsVH, position: Int) {
        val article = items.get(position)
        holder.setData(article)

        holder.itemView.setOnClickListener {
            listener?.onItemClicked(article)
        }

        val isLastItem = position == itemCount - 1
        if (isLastItem) {
            listener?.onLoadMore()
        }
    }


    override fun onViewRecycled(holder: NewsVH) {
        super.onViewRecycled(holder)
        holder.onViewRecycle()
    }


    override fun getItemCount() = items.size()

    interface NewsAdapterListener {
        fun onLoadMore()
        fun onItemClicked(article: Article)
    }

}