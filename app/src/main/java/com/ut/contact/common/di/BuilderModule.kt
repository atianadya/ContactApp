package com.ut.contact.common.di

import com.ut.contact.main.addcontact.AddContactActivity
import com.ut.contact.main.addcontact.AddContactModule
import com.ut.contact.main.detail.DetailActivity
import com.ut.contact.main.detail.DetailModule
import com.ut.contact.main.detail.DetailProvider
import com.ut.contact.main.front.FrontActivity
import com.ut.contact.main.front.FrontModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Atia on 2019-09-03
 */

@Module
abstract class BuilderModule {
    @ContributesAndroidInjector(modules = [(FrontModule::class)])
    abstract fun bindFrontActivity(): FrontActivity

    @ContributesAndroidInjector(modules = [DetailModule::class, DetailProvider::class])
    abstract fun bindDetailActivity(): DetailActivity

    @ContributesAndroidInjector(modules = [(AddContactModule::class)])
    abstract fun bindAddContactActivity(): AddContactActivity
}