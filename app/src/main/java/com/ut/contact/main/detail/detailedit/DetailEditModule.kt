package com.ut.contact.main.detail.detailedit

import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides

/**
 * Created by Atia on 2019-09-04
 */

@Module
class DetailEditModule {
    @Provides
    fun provideViewModel(fragment: DetailEditFragment, factory: DetailEditViewModel.Factory) : DetailEditViewModelType {
        return ViewModelProviders.of(fragment, factory).get(DetailEditViewModel::class.java)
    }

    @Provides
    fun provideWireframe() = DetailEditWireframe()
}