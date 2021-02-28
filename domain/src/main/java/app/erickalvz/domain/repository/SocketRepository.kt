package app.erickalvz.domain.repository

import io.reactivex.Observable

interface SocketRepository {

    fun startService()

    fun sendMessage(title: String)
    val response: Observable<String>
    fun shutDown()
}