package app.erickalvz.presentation.viewmodel

import app.erickalvz.domain.models.Article
import app.erickalvz.domain.models.PagedCollection
import app.erickalvz.domain.repository.NewsRepository
import app.erickalvz.shared.di.scope.ActivityScope
import app.erickalvz.shared.rx.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject


@ActivityScope
class HomeViewModel @Inject constructor(
    private val repository: NewsRepository,
    private val scheduler: SchedulerProvider
) : BaseViewModel<Article>(scheduler) {


    override fun fetch(pageNumber: Int, pageSize: Int): Single<PagedCollection<Article>> {
        return repository.fetchArticles(pageNumber, pageSize)
    }


    override fun filter(title: String) {
        val subscription = Completable.create {
            _filterItems.postValue(
                _items.value?.filter {
                    it.title?.contains(title, true) == true
                }
            )
        }.subscribeOn(scheduler.background).subscribe()
        subscriptions.add(subscription)
    }

    override fun onCleared() {
        subscriptions.clear()
        super.onCleared()
    }
}