package app.erickalvz.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import app.erickalvz.domain.models.PagedCollection
import app.erickalvz.shared.rx.SchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<T>(
    private val scheduler: SchedulerProvider
) : ViewModel() {

    val subscriptions = CompositeDisposable()

    protected val _items = MutableLiveData<List<T>>(emptyList())
    val items: LiveData<List<T>> = _items

    protected val _filterItems = MutableLiveData<List<T>>(emptyList())
    val filterItems: LiveData<List<T>> = _filterItems


    private var currentPage = 1
    private val pageSize = 10

    fun initialLoad() {

        val subscription = fetch(currentPage, pageSize)
            .subscribeOn(scheduler.background)
            .subscribe({
               _items.postValue(it.items)
            }, {
                Log.e("APP__", it.message.toString(), it)
            })

        subscriptions.add(subscription)
    }

    fun loadMore(refresh: Boolean) {

        if (refresh) { currentPage = 1 } else { currentPage += 1 }

        val subscription = fetch(currentPage, pageSize)
            .subscribeOn(scheduler.background)
            .subscribe({
                _items.postValue(it.items)
            }, {
                Log.e("APP__", it.message.toString(), it)
            })
        subscriptions.add(subscription)
    }

    open fun unsubscribe() { subscriptions.clear() }

    abstract fun filter(title: String)

    abstract fun fetch(pageNumber: Int, pageSize: Int): Single<PagedCollection<T>>

}