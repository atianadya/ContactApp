package com.ut.contact.data

import com.ut.contact.model.BaseModel
import com.ut.contact.model.ContactDetailModel
import com.ut.contact.model.ContactsModel
import com.ut.contact.repository.ContactRepository
import io.reactivex.Single
import javax.inject.Inject

class ContactRepositorySource
@Inject constructor(private val factory: ContactDataSource.Factory) : ContactRepository {

    override fun getContacts(): Single<ContactsModel> {
        return factory.network().getContacts()
    }

    override fun saveContact(
        firstName: String,
        lastName: String,
        age: Int,
        photo: String
    ): Single<BaseModel> {
        return factory.network().saveContact(firstName, lastName, age, photo)
    }

    override fun deleteContact(id: String): Single<BaseModel> {
        return factory.network().deleteContact(id)
    }

    override fun editContactDetail(
        id: String,
        firstName: String,
        lastName: String,
        age: Int,
        photo: String
    ): Single<ContactDetailModel> {
        return factory.network().editContactDetail(id, firstName, lastName, age, photo)
    }
}