package com.ut.contact.service

import com.ut.contact.entity.BaseEntity
import com.ut.contact.entity.ContactDetailEntity
import com.ut.contact.entity.ContactsEntity
import io.reactivex.Single

/**
 * Created by Atia on 2019-09-03
 */

interface ContactService {
    fun getContacts(): Single<ContactsEntity>

    fun saveContact(
        firstName: String,
        lastName: String,
        age: Int,
        photo: String
    ): Single<BaseEntity>

    fun deleteContact(id: String): Single<BaseEntity>

    fun editContactDetail(
        id: String,
        firstName: String,
        lastName: String,
        age: Int,
        photo: String
    ): Single<ContactDetailEntity>
}