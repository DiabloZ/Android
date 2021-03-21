package com.suhov.memappwocompose.di

import android.content.Context
import com.suhov.memappwocompose.Constants
import com.suhov.memappwocompose.main.DefaultMainRepositoryInstance
import com.suhov.memappwocompose.main.UniversalRepository
import com.suhov.memappwocompose.model.network.MemApi
import com.suhov.memappwocompose.model.network.util.UnsafeOkHttpClient
import com.suhov.memappwocompose.presentation.ui.BaseApplication
import com.suhov.memappwocompose.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import javax.security.cert.CertificateException


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }

    @Singleton
    @Provides
    fun provideRandomString(): String{
        return "Random String!"
    }

    @Singleton
    @Provides
    fun provideMemApi():MemApi =
        Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(UnsafeOkHttpClient.getUnsafeOkHttpClientBuild())
        .build()
        .create(MemApi::class.java)

    @Singleton
    @Provides
    fun provideMainRepository(api: MemApi):UniversalRepository = DefaultMainRepositoryInstance(api)

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider{
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined

    }
}