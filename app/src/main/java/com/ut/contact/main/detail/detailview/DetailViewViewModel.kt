package com.ut.contact.main.detail.detailview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ut.contact.common.base.ViewModelType
import com.ut.contact.main.front.ContactCardItemViewModel
import javax.inject.Inject

/**
 * Created by Atia on 2019-09-04
 */

interface DetailViewViewModelType : ViewModelType {
    val inputs: Inputs
    val outputs: Outputs

    interface Inputs {}
    interface Outputs {}
}

class DetailViewViewModel : ViewModel(),
    DetailViewViewModelType,
    DetailViewViewModelType.Inputs,
    DetailViewViewModelType.Outputs {

    override val inputs: DetailViewViewModelType.Inputs
        get() = this
    override val outputs: DetailViewViewModelType.Outputs
        get() = this


    class Factory
    @Inject constructor() : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailViewViewModel::class.java)) {
                return DetailViewViewModel() as T
            }
            throw IllegalArgumentException("Unknown viewmodel class")
        }

    }
}