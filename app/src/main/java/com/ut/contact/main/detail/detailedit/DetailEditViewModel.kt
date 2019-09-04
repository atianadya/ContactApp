package com.ut.contact.main.detail.detailedit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ut.contact.common.base.ViewModelType
import com.ut.contact.extension.getErrorMessage
import com.ut.contact.main.front.ContactCardItemViewModel
import com.ut.contact.model.ContactDetailModel
import com.ut.contact.usecase.EditContactDetailUseCase
import io.reactivex.Observable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Created by Atia on 2019-09-04
 */

interface DetailEditViewModelType : ViewModelType {
    val inputs: Inputs
    val outputs: Outputs

    interface Inputs {
        fun onViewLoaded(data: ContactCardItemViewModel)
        fun onChangedFirstName(text: String)
        fun onChangedLastName(text: String)
        fun onChangedAge(text: String)
        fun onChangedPhotoUrl(text: String)
        fun onClickSave()
    }

    interface Outputs {
        val shouldShowOutput: Observable<String>
        val shouldBackToMain: Observable<Boolean>
        val shouldShowLoading: Observable<Boolean>
    }
}

class DetailEditViewModel(private val editContactDetailUseCase: EditContactDetailUseCase) : ViewModel(),
    DetailEditViewModelType,
    DetailEditViewModelType.Inputs,
    DetailEditViewModelType.Outputs {

    lateinit var data: ContactCardItemViewModel

    val showOutputSubject = PublishSubject.create<String>()
    val backToMainSubject = PublishSubject.create<Boolean>()
    val showLoadingSubject = PublishSubject.create<Boolean>()

    override val inputs: DetailEditViewModelType.Inputs
        get() = this
    override val outputs: DetailEditViewModelType.Outputs
        get() = this

    override val shouldShowOutput: Observable<String>
        get() = showOutputSubject
    override val shouldBackToMain: Observable<Boolean>
        get() = backToMainSubject
    override val shouldShowLoading: Observable<Boolean>
        get() = showLoadingSubject

    override fun onViewLoaded(data: ContactCardItemViewModel) {
        this.data = data
    }

    override fun onChangedFirstName(text: String) {
        data.firstName = text
    }

    override fun onChangedLastName(text: String) {
        data.lastName = text
    }

    override fun onChangedAge(text: String) {
        if (!text.isNullOrEmpty()) {
            data.age = text.toInt()
        }
    }

    override fun onChangedPhotoUrl(text: String) {
        data.photo = text
    }

    override fun onClickSave() {
        showLoadingSubject.onNext(true)
        editContactDetailUseCase.execute(EditContactDetailObserver(), EditContactDetailUseCase.Param(
            id = data.id,
            firstName = data.firstName,
            lastName = data.lastName,
            age = data.age,
            photoUrl = data.photo
        ))
    }

    class Factory
    @Inject constructor(val editContactDetailUseCase: EditContactDetailUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailEditViewModel::class.java)) {
                return DetailEditViewModel(editContactDetailUseCase) as T
            }
            throw IllegalArgumentException("Unknown viewmodel class")
        }
    }

    inner class EditContactDetailObserver : DisposableSingleObserver<ContactDetailModel>() {
        override fun onSuccess(t: ContactDetailModel) {
            showLoadingSubject.onNext(false)
            showOutputSubject.onNext(t.message)
            backToMainSubject.onNext(true)
        }

        override fun onError(e: Throwable) {
            showLoadingSubject.onNext(false)
            showOutputSubject.onNext(e.getErrorMessage())
        }
    }
}