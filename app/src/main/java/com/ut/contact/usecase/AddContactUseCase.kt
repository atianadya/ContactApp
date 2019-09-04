package com.ut.contact.usecase

import com.ut.contact.model.BaseModel
import com.ut.contact.repository.ContactRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Atia on 2019-09-04
 */

class AddContactUseCase
@Inject constructor(private val repository: ContactRepository,
                    @Named("SCHEDULER_EXECUTION") threadException: Scheduler,
                    @Named("SCHEDULER_POST_EXECUTION") postExecutionThread: Scheduler
) : UseCase.RxSingle<BaseModel, AddContactUseCase.Param>(threadException, postExecutionThread) {
    override fun build(params: Param?): Single<BaseModel> {
        params?.let {
            return repository.saveContact(
                it.firstName,
                it.lastName,
                it.age,
                it.photo
            )
        }
        throw RuntimeException("AddContactUseCase param not available")
    }

    data class Param(
        val firstName: String,
        val lastName: String,
        val age: Int,
        val photo: String
    )
}