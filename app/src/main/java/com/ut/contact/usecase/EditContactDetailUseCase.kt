package com.ut.contact.usecase

import com.ut.contact.model.ContactDetailModel
import com.ut.contact.repository.ContactRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Atia on 2019-09-04
 */

class EditContactDetailUseCase
@Inject constructor(private val repository: ContactRepository,
                    @Named("SCHEDULER_EXECUTION") threadException: Scheduler,
                    @Named("SCHEDULER_POST_EXECUTION") postExecutionThread: Scheduler
) : UseCase.RxSingle<ContactDetailModel, EditContactDetailUseCase.Param>(threadException, postExecutionThread) {
    override fun build(params: Param?): Single<ContactDetailModel> {
        params?.let {
            return repository.editContactDetail(
                id = it.id,
                firstName = it.firstName,
                lastName = it.lastName,
                age = it.age,
                photo = it.photoUrl)
        }
        throw RuntimeException("EditContactDetailUseCase param not available")
    }

    data class Param(
        val id: String,
        val firstName: String,
        val lastName: String,
        val age: Int,
        val photoUrl: String
    )
}