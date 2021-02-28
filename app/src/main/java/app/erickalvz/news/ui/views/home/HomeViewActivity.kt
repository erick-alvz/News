package app.erickalvz.news.ui.views.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import app.erickalvz.domain.models.Article
import app.erickalvz.news.R
import app.erickalvz.news.di.appComponent

import app.erickalvz.news.di.components.HomeComponent
import app.erickalvz.news.ui.views.article.ArticleView
import app.erickalvz.presentation.viewmodel.HomeViewModel

import javax.inject.Inject

class HomeViewActivity : AppCompatActivity() {


    lateinit var homeViewComponent: HomeComponent

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        homeViewComponent = appComponent.buildActivityComponent().create()
        homeViewComponent.inject(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home_view)


        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)


        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, HomeView.newInstance(), HomeView::class.java.simpleName)
        transaction.commit()


    }

    fun navigateTo(article: Article) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, ArticleView.newInstance(article), "Tag")
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onStart() {
        super.onStart()
        //viewModel.initialLoad()
    }

}