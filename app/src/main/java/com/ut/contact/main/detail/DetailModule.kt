package com.ut.contact.main.detail

import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides

/**
 * Created by Atia on 2019-09-04
 */

@Module
class DetailModule {

    @Provides
    fun provideViewModel(activity: DetailActivity, factory: DetailViewModel.Factory) : DetailViewModelType {
        return ViewModelProviders.of(activity, factory).get(DetailViewModel::class.java)
    }

    @Provides
    fun provideWireframe() = DetailWireframe()
}