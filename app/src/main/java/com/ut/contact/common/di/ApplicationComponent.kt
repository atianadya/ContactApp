package com.ut.contact.common.di

import android.app.Application
import com.ut.contact.ContactApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Atia on 2019-09-03
 */

@Singleton
@Component(modules = [
    (AndroidSupportInjectionModule::class),
    (BuilderModule::class),
    (AppModule::class)])
interface ApplicationComponent {
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(app: Application): Builder
        fun build(): ApplicationComponent
    }

    fun inject(app: ContactApplication)
}