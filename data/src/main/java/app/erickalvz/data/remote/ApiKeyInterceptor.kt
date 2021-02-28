package app.erickalvz.data.remote

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ApiKeyInterceptor @Inject constructor(private val  apiKEY: String) : Interceptor  {


    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()

        val modifiedRequest = request.newBuilder()
            .addHeader("Authorization", apiKEY)
            .build()

        return chain.proceed(modifiedRequest)
    }
}