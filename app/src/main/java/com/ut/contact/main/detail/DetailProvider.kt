package com.ut.contact.main.detail

import com.ut.contact.main.detail.detailedit.DetailEditFragment
import com.ut.contact.main.detail.detailedit.DetailEditModule
import com.ut.contact.main.detail.detailview.DetailViewFragment
import com.ut.contact.main.detail.detailview.DetailViewModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Atia on 2019-09-04
 */

@Module
abstract class DetailProvider {
    @ContributesAndroidInjector(modules = [(DetailViewModule::class)])
    abstract fun bindDetailViewFragment() : DetailViewFragment

    @ContributesAndroidInjector(modules = [(DetailEditModule::class)])
    abstract fun bindDetailEditFragment() : DetailEditFragment
}