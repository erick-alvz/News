package app.erickalvz.news.ui.views.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import app.erickalvz.domain.models.Article
import app.erickalvz.news.databinding.FragmentHomeViewBinding
import app.erickalvz.news.ui.adapters.NewsAdapter
import app.erickalvz.presentation.viewmodel.HomeViewModel
import javax.inject.Inject


class HomeView : Fragment(), NewsAdapter.NewsAdapterListener, SwipeRefreshLayout.OnRefreshListener {

    private var binding: FragmentHomeViewBinding? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: HomeViewModel

    private lateinit var adapter: NewsAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity as HomeViewActivity).homeViewComponent.inject(this)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        adapter = NewsAdapter()
    }

    private var isFilter = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeViewBinding.inflate(inflater, container, false)


        binding?.let {
            it.rvPhotos.layoutManager = LinearLayoutManager(requireContext())
            it.rvPhotos.setHasFixedSize(true)
            it.rvPhotos.adapter = this@HomeView.adapter
        }

        binding?.edtSearch?.addTextChangedListener {

            if (!it.isNullOrBlank()) {
                isFilter = true
                viewModel.filter(it.toString())
            }

        }

        binding?.swipeRefresh?.setOnRefreshListener(this)

        return binding?.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.items.observe(this, { items ->
            binding?.progressCircular?.postDelayed({
                binding?.progressCircular?.visibility = View.GONE
                binding?.swipeRefresh?.isRefreshing = false
                adapter.addItems(items)
            }, 1000)

        })

        viewModel.filterItems.observe(this, {
            if (isFilter) {
                adapter.replace(it)
            }
        })


    }

    override fun onStart() {
        super.onStart()
        adapter.addListener(this)
    }

    override fun onItemClicked(article: Article) {
        (activity as HomeViewActivity).navigateTo(article)
    }

    override fun onRefresh() {
        viewModel.loadMore(true)
    }

    override fun onLoadMore() {
        binding?.progressCircular?.visibility = View.VISIBLE
        viewModel.loadMore(false)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.unsubscribe()
        binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() = HomeView()

    }




}