package com.ut.contact.repository

import com.ut.contact.model.BaseModel
import com.ut.contact.model.ContactDetailModel
import com.ut.contact.model.ContactsModel
import io.reactivex.Single

/**
 * Created by Atia on 2019-09-03
 */

interface ContactRepository {
    fun getContacts(): Single<ContactsModel>

    fun saveContact(
        firstName: String,
        lastName: String,
        age: Int,
        photo: String
    ): Single<BaseModel>

    fun deleteContact(id: String): Single<BaseModel>

    fun editContactDetail(
        id: String,
        firstName: String,
        lastName: String,
        age: Int,
        photo: String
    ): Single<ContactDetailModel>
}