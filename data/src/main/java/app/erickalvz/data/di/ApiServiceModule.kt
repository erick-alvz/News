package app.erickalvz.data.di

import app.erickalvz.data.remote.services.IApiService
import app.erickalvz.data.remote.services.ISocketService
import app.erickalvz.data.remote.soket.SocketService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiServiceModule {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): IApiService{
        return retrofit.create(IApiService::class.java)
    }


    @Singleton
    @Provides
    fun provideSocketService(@Named("socketClient") client: OkHttpClient): ISocketService{
        return SocketService(client)
    }

}