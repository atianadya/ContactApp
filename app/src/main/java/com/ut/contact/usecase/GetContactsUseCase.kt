package com.ut.contact.usecase

import com.ut.contact.model.ContactsModel
import com.ut.contact.repository.ContactRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Atia on 2019-09-03
 */

class GetContactsUseCase
@Inject constructor(private val repository: ContactRepository,
                    @Named("SCHEDULER_EXECUTION") threadException: Scheduler,
                    @Named("SCHEDULER_POST_EXECUTION") postExecutionThread: Scheduler)
    : UseCase.RxSingle<ContactsModel, GetContactsUseCase.Param>(threadException, postExecutionThread) {

    override fun build(params: Param?): Single<ContactsModel> {
        return repository.getContacts()
    }

    object Param
}