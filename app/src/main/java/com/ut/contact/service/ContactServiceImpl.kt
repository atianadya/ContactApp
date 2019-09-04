package com.ut.contact.service

import com.ut.contact.entity.BaseEntity
import com.ut.contact.entity.ContactDetailEntity
import com.ut.contact.entity.ContactsEntity
import com.ut.contact.network.ContactApi
import com.ut.contact.request.SaveContactRequest
import io.reactivex.Single
import retrofit2.Retrofit

class ContactServiceImpl(private val retrofit: Retrofit) : ContactService {

    private val service by lazy { retrofit.create(ContactApi::class.java) }

    private val header = mutableMapOf<String, String>().apply {
        put("Accept", "application/json")
    }

    override fun getContacts(): Single<ContactsEntity> {
        return service.getContacts(
            headers = header,
            url = "/contact"
        )
    }

    override fun saveContact(
        firstName: String,
        lastName: String,
        age: Int,
        photo: String
    ): Single<BaseEntity> {
        val body = SaveContactRequest(firstName, lastName, age, photo)
        return service.saveContact(
            headers = header,
            url = "/contact",
            body = body
        )
    }

    override fun deleteContact(id: String): Single<BaseEntity> {
        return service.deleteContact(
            headers = header,
            url = "/contact/${id}"
        )
    }

    override fun editContactDetail(
        id: String,
        firstName: String,
        lastName: String,
        age: Int,
        photo: String
    ): Single<ContactDetailEntity> {
        val body = SaveContactRequest(firstName, lastName, age, photo)
        return service.editContactDetail(
            headers = header,
            url = "/contact/${id}",
            body = body
        )
    }
}