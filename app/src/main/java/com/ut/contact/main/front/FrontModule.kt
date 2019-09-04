package com.ut.contact.main.front

import androidx.lifecycle.ViewModelProviders
import com.ut.contact.main.front.listitem.ContactListItem
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import dagger.Module
import dagger.Provides

/**
 * Created by Atia on 2019-09-03
 */

@Module
class FrontModule {
    @Provides
    fun provideViewModel(
        activity: FrontActivity,
        factory: FrontViewModel.Factory
    ): FrontViewModelType {
        return ViewModelProviders.of(activity, factory).get(FrontViewModel::class.java)
    }

    @Provides
    fun provideWireframe() = FrontWireframe()

    @Provides
    fun provideAdapter() = FastItemAdapter<ContactListItem>()
}