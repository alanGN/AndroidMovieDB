package com.example.alan_pc.droidtv.di.module

import android.app.Application
import com.example.alan_pc.androidmoviedb.BuildConfig
import com.example.alan_pc.androidmoviedb.data.api.TmdbApi
import com.example.alan_pc.androidmoviedb.data.converter.EmptyBodyConverterFactory
import com.example.alan_pc.droidtv.di.NamedProperties
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by ALAN-PC on 29/07/2018
 */
@Module
class NetworkModule {

    private val TIME_OUT: Long = 10 * 10 * 1024 //1 min
    private val NETWORK_CACHE_FOLDER = "http"

    @Provides
    @Singleton
    @Named(NamedProperties.BASE_URL)
    internal fun provideBaseUrl(): String {
        return BuildConfig.WB_BASE_SERVICE
    }

    @Provides
    @Singleton
    internal fun provideCache(application: Application): Cache {
        return Cache(File(application.cacheDir, NETWORK_CACHE_FOLDER), TIME_OUT)
    }

    @Provides
    @Singleton
    fun provideHttpClient(cache: Cache): OkHttpClient {
        val builder = OkHttpClient.Builder()

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(loggingInterceptor)

        builder.addInterceptor(loggingInterceptor)
        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        builder.readTimeout(TIME_OUT, TimeUnit.SECONDS)
        builder.cache(cache)
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(@Named(NamedProperties.BASE_URL) baseUrl: String, httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(EmptyBodyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build()
    }

    @Provides
    @Singleton
    fun providesExperiencesApi(retrofit: Retrofit): TmdbApi {
        return retrofit.create(TmdbApi::class.java)
    }

}