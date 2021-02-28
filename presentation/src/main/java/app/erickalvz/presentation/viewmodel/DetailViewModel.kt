package app.erickalvz.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.erickalvz.domain.repository.SocketRepository
import app.erickalvz.shared.di.scope.ActivityScope
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@ActivityScope
class DetailViewModel @Inject constructor(
    private val repository: SocketRepository
) : ViewModel() {


    private val _item = MutableLiveData<String>()

    val item: LiveData<String> = _item

    private val subscriptions = CompositeDisposable()


    fun startService() {
        repository.startService()
        subscriptions.add(repository.response.subscribe({ _item.postValue(it) }, { }))
    }


    fun sendMessage(title: String?) =  repository.sendMessage(title ?: "")


    fun unsubscribe() {
        subscriptions.clear()
        repository.shutDown()
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }

}