package com.ut.contact.main.front

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ut.contact.R
import com.ut.contact.common.base.ViewModelType
import com.ut.contact.model.ContactsModel
import com.ut.contact.usecase.GetContactsUseCase
import io.reactivex.Observable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Created by Atia on 2019-09-03
 */

interface FrontViewModelType : ViewModelType {
    val inputs: Inputs
    val outputs: Outputs

    interface Inputs {
        fun onViewLoaded()
        fun onRefresh()
    }

    interface Outputs {
        val shouldShowItems: Observable<List<ContactCardItemViewModel>>
        val shouldShowLoading: Observable<Boolean>
        val shouldShowOutput: Observable<Int>
        val shouldShowRefresh: Observable<Boolean>
    }
}

class FrontViewModel(private val getContactsUseCase: GetContactsUseCase) : ViewModel(),
    FrontViewModelType,
    FrontViewModelType.Inputs,
    FrontViewModelType.Outputs {

    private var showItemsSubject = PublishSubject.create<List<ContactCardItemViewModel>>()
    private var showLoadingSubject = PublishSubject.create<Boolean>()
    private var showOutputSubject = PublishSubject.create<Int>()
    private var showRefreshSubject = PublishSubject.create<Boolean>()

    override val inputs: FrontViewModelType.Inputs
        get() = this
    override val outputs: FrontViewModelType.Outputs
        get() = this

    override val shouldShowItems: Observable<List<ContactCardItemViewModel>>
        get() = showItemsSubject
    override val shouldShowLoading: Observable<Boolean>
        get() = showLoadingSubject
    override val shouldShowOutput: Observable<Int>
        get() = showOutputSubject
    override val shouldShowRefresh: Observable<Boolean>
        get() = showRefreshSubject

    override fun onViewLoaded() {
        showLoadingSubject.onNext(true)
        getContactsUseCase.execute(GetContactsObserver())
    }

    override fun onRefresh() {
        getContactsUseCase.execute(GetContactsObserver())
    }

    class Factory
    @Inject constructor(private val getContactsUseCase: GetContactsUseCase) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FrontViewModel::class.java)) {
                return FrontViewModel(getContactsUseCase) as T
            }
            throw IllegalArgumentException("Unknown viewmodel class")
        }
    }

    inner class GetContactsObserver : DisposableSingleObserver<ContactsModel>() {
        override fun onSuccess(t: ContactsModel) {
            showLoadingSubject.onNext(false)
            showRefreshSubject.onNext(false)
            val items = mutableListOf<ContactCardItemViewModel>()
            val contactList = t.data
            contactList.map {
                items.add(
                    ContactCardItemViewModel(
                        id = it.id,
                        photo = it.photo,
                        firstName = it.firstName,
                        lastName = it.lastName,
                        age = it.age
                    )
                )
            }

            showItemsSubject.onNext(items)
        }

        override fun onError(e: Throwable) {
            showRefreshSubject.onNext(false)
            showLoadingSubject.onNext(false)
            showOutputSubject.onNext(R.string.generic_error_message)
        }
    }
}