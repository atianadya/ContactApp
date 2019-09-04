package com.ut.contact.entity

import com.ut.contact.model.ContactDetailModel
import com.ut.contact.model.ContactsModel
import com.google.gson.annotations.SerializedName

/**
 * Created by Atia on 2019-09-03
 */

data class ContactDetailEntity(
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: ContactsModel.Data) {

    fun create() : ContactDetailModel {
        return ContactDetailModel(
            message = message,
            data = ContactsModel.Data(
                id = data.id,
                firstName = data.firstName,
                lastName = data.lastName,
                age = data.age,
                photo = data.photo
            )
        )
    }
}