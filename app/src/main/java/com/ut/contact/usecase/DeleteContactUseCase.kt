package com.ut.contact.usecase

import com.ut.contact.model.BaseModel
import com.ut.contact.repository.ContactRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Atia on 2019-09-04
 */

class DeleteContactUseCase
@Inject constructor(private val repository: ContactRepository,
                    @Named("SCHEDULER_EXECUTION") threadException: Scheduler,
                    @Named("SCHEDULER_POST_EXECUTION") postExecutionThread: Scheduler
) : UseCase.RxSingle<BaseModel, DeleteContactUseCase.Param>(threadException, postExecutionThread) {
    override fun build(params: Param?): Single<BaseModel> {
        params?.let {
            return repository.deleteContact(it.id)
        }

        throw RuntimeException("DeleteContactUseCase param not available")
    }

    data class Param(
        val id: String
    )
}