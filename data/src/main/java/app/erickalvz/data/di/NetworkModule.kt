package app.erickalvz.data.di

import app.erickalvz.data.remote.ApiKeyInterceptor
import app.erickalvz.data.remote.services.IApiService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {



    @Provides
    @Singleton
    fun provideJacksonMapper() = jacksonObjectMapper()


    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient, apiURL: String, mapper: ObjectMapper): Retrofit =  Retrofit.Builder()
        .client(client)
        .baseUrl(apiURL)
        .addConverterFactory(JacksonConverterFactory.create(mapper))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()


    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: Interceptor, @Named("ApiKeyInterceptor") apiKeyInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(apiKeyInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    @Provides
    @Singleton
    @Named("ApiKeyInterceptor")
    fun provideApiKeyInterceptor(@Named("apiKEY") apiKEY: String): Interceptor {
        return ApiKeyInterceptor(apiKEY)
    }


    @Singleton
    @Provides
    @Named("socketClient")
    fun provideSocketClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }



}