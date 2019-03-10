package com.testapp.babylonplaceholder.data.di

import com.testapp.babylonplaceholder.data.network.PlaceholderApi
import com.testapp.babylonplaceholder.data.repository.PlaceholderRepositoryImpl
import com.testapp.babylonplaceholder.domain.repository.PlaceholderRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val baseUrl = "https://jsonplaceholder.typicode.com/"

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePlaceholderApi(retrofit: Retrofit): PlaceholderApi {
        return retrofit.create<PlaceholderApi>(PlaceholderApi::class.java)
    }


    @Provides
    internal fun providePlaceHolderRepository(api: PlaceholderApi): PlaceholderRepository {
        return PlaceholderRepositoryImpl(api)
    }
}
