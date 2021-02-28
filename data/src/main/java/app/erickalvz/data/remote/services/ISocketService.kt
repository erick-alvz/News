package app.erickalvz.data.remote.services

import io.reactivex.Observable

interface ISocketService {

    fun start()

    fun sendMessage(title: String)

    val responseReceive: Observable<String>

    fun close()


}