package com.ut.contact.entity

import com.ut.contact.model.ContactsModel
import com.google.gson.annotations.SerializedName

/**
 * Created by Atia on 2019-09-03
 */

data class ContactsEntity(
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: List<Data>) {

    data class Data(
        @SerializedName("id") val id: String,
        @SerializedName("firstName") val firstName: String,
        @SerializedName("lastName") val lastName: String,
        @SerializedName("age") val age: Int,
        @SerializedName("photo") val photo: String
    )

    fun create(): ContactsModel {
        return ContactsModel(
            message = message,
            data = data.map {
                ContactsModel.Data(
                    id = it.id,
                    firstName = it.firstName,
                    lastName = it.lastName,
                    age = it.age,
                    photo = it.photo
                )
            }
        )
    }
}