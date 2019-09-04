package com.ut.contact.main.addcontact

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ut.contact.R
import com.ut.contact.common.base.ViewModelType
import com.ut.contact.extension.getErrorMessage
import com.ut.contact.model.BaseModel
import com.ut.contact.usecase.AddContactUseCase
import io.reactivex.Observable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Created by Atia on 2019-09-04
 */

interface AddContactViewModelType : ViewModelType {
    val inputs: Inputs
    val outputs: Outputs

    interface Inputs{
        fun onChangedFirstName(text: String)
        fun onChangedLastName(text: String)
        fun onChangedAge(text: String)
        fun onChangedPhoto(text: String)
        fun onClickAdd()
    }
    interface Outputs{
        val shouldShowOutput: Observable<String>
        val shouldBackToHome: Observable<Boolean>
        val shouldShowLoading: Observable<Boolean>
    }
}

class AddContactViewModel(private val addContactUseCase: AddContactUseCase) : ViewModel(),
    AddContactViewModelType,
    AddContactViewModelType.Inputs,
    AddContactViewModelType.Outputs {

    var firstName: String? = null
    var lastName: String? = null
    var age: Int? = null
    var photo: String? = null

    val showOutputSubject = PublishSubject.create<String>()
    val backToHomeSubject = PublishSubject.create<Boolean>()
    val showLoadingSubject = PublishSubject.create<Boolean>()

    override val inputs: AddContactViewModelType.Inputs
        get() = this
    override val outputs: AddContactViewModelType.Outputs
        get() = this

    override val shouldShowOutput: Observable<String>
        get() = showOutputSubject
    override val shouldBackToHome: Observable<Boolean>
        get() = backToHomeSubject
    override val shouldShowLoading: Observable<Boolean>
        get() = showLoadingSubject

    override fun onChangedFirstName(text: String) {
        firstName = text
    }

    override fun onChangedLastName(text: String) {
        lastName = text
    }

    override fun onChangedAge(text: String) {
        if (!text.isNullOrEmpty()) { age = text.toInt() }
    }

    override fun onChangedPhoto(text: String) {
        photo = text
    }

    override fun onClickAdd() {
        showLoadingSubject.onNext(true)
        addContactUseCase.execute(AddContactObserver(), AddContactUseCase.Param(
            firstName.orEmpty(),
            lastName.orEmpty(),
            age ?: -1,
            photo.orEmpty()
        ))
    }

    class Factory
    @Inject constructor(private val addContactUseCase: AddContactUseCase): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AddContactViewModel::class.java)) {
                return AddContactViewModel(addContactUseCase) as T
            }
            throw IllegalArgumentException("Unknown viewmodel class")
        }
    }

    inner class AddContactObserver: DisposableSingleObserver<BaseModel>() {
        override fun onSuccess(t: BaseModel) {
            showLoadingSubject.onNext(false)
            showOutputSubject.onNext(t.message)
            backToHomeSubject.onNext(true)
        }

        override fun onError(e: Throwable) {
            showLoadingSubject.onNext(false)
            showOutputSubject.onNext(e.getErrorMessage())
        }
    }
}