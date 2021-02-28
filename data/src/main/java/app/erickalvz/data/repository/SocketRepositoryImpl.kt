package app.erickalvz.data.repository

import app.erickalvz.data.remote.services.ISocketService
import app.erickalvz.domain.repository.SocketRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
internal class SocketRepositoryImpl @Inject constructor(
    private val iSocketService: ISocketService
) : SocketRepository {


    override fun startService() {
        iSocketService.start()
    }

    override fun sendMessage(title: String) {
        iSocketService.sendMessage(title)
    }


    override val response: Observable<String>
        get() = iSocketService.responseReceive

    override fun shutDown() {
        iSocketService.close()
    }

}