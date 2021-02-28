package app.erickalvz.data.remote.soket

import app.erickalvz.data.remote.services.ISocketService
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import okhttp3.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SocketService @Inject constructor(
    private val client: OkHttpClient
) : ISocketService, WebSocketListener() {


    private val subject = PublishSubject.create<String>()

    private lateinit var socket: WebSocket

    override fun start() {

        val request = Request.Builder()
            .url("ws://echo.websocket.org")
            .build()

        socket = client.newWebSocket(request, this)

    }

    override fun sendMessage(title: String) {
        socket.send(title)
    }

    override val responseReceive: Observable<String>
        get() = subject



    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)
        subject.onNext("$text, via. Socket")

    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)
        subject.onError(Throwable(response?.message, t))
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)
    }


    override fun close() {
        socket.close(1000, "SessionEnd")
    }



}