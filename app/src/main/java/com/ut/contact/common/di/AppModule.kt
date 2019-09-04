package com.ut.contact.common.di

import com.ut.contact.BuildConfig
import com.ut.contact.data.ContactRepositorySource
import com.ut.contact.repository.ContactRepository
import com.ut.contact.service.ContactService
import com.ut.contact.service.ContactServiceImpl
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Atia on 2019-09-03
 */

@Module
class AppModule {

    @Provides
    @Singleton
    @Named("APP_BASE_URL")
    fun provideBaseUrl() = BuildConfig.base_url

    @Provides
    @Named("SCHEDULER_EXECUTION")
    fun provideExecutionScheduler() = Schedulers.io()

    @Provides
    @Named("SCHEDULER_POST_EXECUTION")
    fun providePostExecutionScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    @Named("RetrofitClient")
    fun provideRetrofit(@Named("APP_BASE_URL") baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideNetworkService(@Named("RetrofitClient") retrofit: Retrofit): ContactService {
        return ContactServiceImpl(retrofit)
    }

    @Provides
    @Singleton
    fun provideNetworkRepository(networkRepositorySource: ContactRepositorySource): ContactRepository =
        networkRepositorySource
}