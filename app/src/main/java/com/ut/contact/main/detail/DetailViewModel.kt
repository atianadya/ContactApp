package com.ut.contact.main.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ut.contact.common.base.ViewModelType
import com.ut.contact.extension.getErrorMessage
import com.ut.contact.main.front.ContactCardItemViewModel
import com.ut.contact.model.BaseModel
import com.ut.contact.usecase.DeleteContactUseCase
import io.reactivex.Observable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Created by Atia on 2019-09-04
 */

interface DetailViewModelType : ViewModelType {
    val inputs: Inputs
    val outputs: Outputs

    interface Inputs {
        fun onViewLoaded(detailData: ContactCardItemViewModel)
        fun onClickDelete()
    }

    interface Outputs {
        val shouldShowOutput: Observable<String>
        val shouldBackToHome: Observable<Boolean>
    }
}

class DetailViewModel(private val deleteContactUseCase: DeleteContactUseCase) : ViewModel(),
    DetailViewModelType,
    DetailViewModelType.Inputs,
    DetailViewModelType.Outputs {

    lateinit var detailData: ContactCardItemViewModel

    val showOutputSubject = PublishSubject.create<String>()
    val backToHomeSubject = PublishSubject.create<Boolean>()

    override val inputs: DetailViewModelType.Inputs
        get() = this
    override val outputs: DetailViewModelType.Outputs
        get() = this

    override val shouldShowOutput: Observable<String>
        get() = showOutputSubject
    override val shouldBackToHome: Observable<Boolean>
        get() = backToHomeSubject

    override fun onViewLoaded(detailData: ContactCardItemViewModel) {
        this.detailData = detailData
    }

    override fun onClickDelete() {
        deleteContactUseCase.execute(DeleteContactObserver(),
            DeleteContactUseCase.Param(detailData.id)
        )
    }

    class Factory
    @Inject constructor(private val deleteContactUseCase: DeleteContactUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
                return DetailViewModel(deleteContactUseCase) as T
            }
            throw IllegalArgumentException("Unknown viewmodel class")
        }
    }

    inner class DeleteContactObserver : DisposableSingleObserver<BaseModel>() {
        override fun onSuccess(t: BaseModel) {
            showOutputSubject.onNext(t.message)
            backToHomeSubject.onNext(true)
        }

        override fun onError(e: Throwable) {
            showOutputSubject.onNext(e.getErrorMessage())
        }

    }
}