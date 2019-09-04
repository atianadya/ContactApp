package com.ut.contact.network

import com.ut.contact.entity.BaseEntity
import com.ut.contact.entity.ContactDetailEntity
import com.ut.contact.entity.ContactsEntity
import com.ut.contact.request.SaveContactRequest
import io.reactivex.Single
import retrofit2.http.*

/**
 * Created by Atia on 2019-09-03
 */

interface ContactApi {
    @GET
    fun getContacts(
        @HeaderMap headers: Map<String, String>,
        @Url() url: String
    ): Single<ContactsEntity>

    @POST
    fun saveContact(
        @HeaderMap headers: Map<String, String>,
        @Url() url: String,
        @Body body: SaveContactRequest
    ): Single<BaseEntity>

    @DELETE
    fun deleteContact(
        @HeaderMap headers: Map<String, String>,
        @Url() url: String
    ): Single<BaseEntity>

    @PUT
    fun editContactDetail(
        @HeaderMap headers: Map<String, String>,
        @Url() url: String,
        @Body body: SaveContactRequest
    ): Single<ContactDetailEntity>
}