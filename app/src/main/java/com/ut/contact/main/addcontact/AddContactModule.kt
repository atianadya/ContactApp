package com.ut.contact.main.addcontact

import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides

/**
 * Created by Atia on 2019-09-04
 */

@Module
class AddContactModule {
    @Provides
    fun provideViewModel(activity: AddContactActivity, factory: AddContactViewModel.Factory) : AddContactViewModelType {
        return ViewModelProviders.of(activity, factory).get(AddContactViewModel::class.java)
    }

    @Provides
    fun provideWireframe() = AddContactWireframe()
}