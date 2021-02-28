package app.erickalvz.news.ui.views.article

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import app.erickalvz.domain.models.Article
import app.erickalvz.news.databinding.FragmentArticleViewBinding
import app.erickalvz.news.ui.views.home.HomeViewActivity
import app.erickalvz.presentation.viewmodel.DetailViewModel
import app.erickalvz.presentation.viewmodel.HomeViewModel
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import javax.inject.Inject


class ArticleView : Fragment() {

    private var binding: FragmentArticleViewBinding? = null

    private var article: Article? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: DetailViewModel


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as HomeViewActivity).homeViewComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { article = it.getParcelable(ARTICLE) }

        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        viewModel.startService()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentArticleViewBinding.inflate(inflater, container, false)
        return binding?.root
    }


    private var showChanges = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding?.item?.title?.text = article?.title
        binding?.item?.description?.text = article?.description
        binding?.item?.source?.text = article?.source

        article?.urlToImage?.let {
            Picasso.get().load(it).into(binding?.item?.image)
        }


        binding?.text?.addTextChangedListener {
            showChanges = true
            viewModel.sendMessage(it.toString())
        }


        viewModel.item.observe(this, { title ->
            if (showChanges) { binding?.item?.title?.text = title }
        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.unsubscribe()
    }

    companion object {

        private const val ARTICLE = "article"

        @JvmStatic
        fun newInstance(article: Article) = ArticleView().apply {
            arguments = Bundle().apply {
                putParcelable(ARTICLE, article)
            }
        }


    }
}