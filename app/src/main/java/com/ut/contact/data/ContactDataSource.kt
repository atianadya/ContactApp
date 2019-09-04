package com.ut.contact.data

import com.ut.contact.model.BaseModel
import com.ut.contact.model.ContactDetailModel
import com.ut.contact.model.ContactsModel
import com.ut.contact.repository.ContactRepository
import com.ut.contact.service.ContactService
import dagger.Lazy
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Atia on 2019-09-03
 */

interface ContactDataSource : ContactRepository {

    class Factory
    @Inject constructor(private val network: Lazy<Network>) {
        fun network(): Network = network.get()
    }

    class Network
    @Inject constructor(private val service: ContactService) : ContactDataSource {
        override fun getContacts(): Single<ContactsModel> {
            return service.getContacts().map { it.create() }
        }

        override fun saveContact(
            firstName: String,
            lastName: String,
            age: Int,
            photo: String
        ): Single<BaseModel> {
            return service.saveContact(firstName, lastName, age, photo).map { it.create() }
        }

        override fun deleteContact(id: String): Single<BaseModel> {
            return service.deleteContact(id).map { it.create() }
        }

        override fun editContactDetail(
            id: String,
            firstName: String,
            lastName: String,
            age: Int,
            photo: String
        ): Single<ContactDetailModel> {
            return service.editContactDetail(id, firstName, lastName, age, photo).map { it.create() }
        }
    }
}