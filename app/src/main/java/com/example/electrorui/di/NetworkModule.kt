package com.example.electrorui.di

import com.example.electrorui.networkApi.ApisServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

//    @Singleton @Provides
//    fun provideRetrofit(): Retrofit{
//        return Retrofit.Builder()
////            .baseUrl("http://192.168.0.21:8080/")
////            .baseUrl("https://ruie.dgcvm.com/")
////            .baseUrl("https://172.16.16.166/")
//            .baseUrl("https://ruie.dgcor.com/")
//            .client(okHttpClient())
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    @Singleton @Provides
//    fun provideApiClient(retrofit: Retrofit) : ApisServices{
//        return retrofit.create(ApisServices::class.java)
//    }
    const val url1 = "https://ruie.dgcor.com/"
    const val url2 = "https://ruie2025.dgcor.com/"

    @Provides @Singleton @Named("primary")
    fun providePrimaryRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(url1)
        .client(okHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides @Singleton @Named("secondary")
    fun provideSecondaryRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(url2)
        .client(okHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides @Singleton @Named("primary")
    fun providePrimaryApi(@Named("primary") retrofit: Retrofit): ApisServices =
        retrofit.create(ApisServices::class.java)

    // ApiService secundaria
    @Provides @Singleton @Named("secondary")
    fun provideSecondaryApi(@Named("secondary") retrofit: Retrofit): ApisServices =
        retrofit.create(ApisServices::class.java)

    private fun okHttpClient() = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()

}