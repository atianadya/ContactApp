package com.ut.contact.main.detail.detailview

import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides

/**
 * Created by Atia on 2019-09-04
 */

@Module
class DetailViewModule {
    @Provides
    fun provideViewModel(fragment: DetailViewFragment, factory: DetailViewViewModel.Factory) : DetailViewViewModelType {
        return ViewModelProviders.of(fragment, factory).get(DetailViewViewModel::class.java)
    }
}