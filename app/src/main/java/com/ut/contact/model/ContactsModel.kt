package com.ut.contact.model

/**
 * Created by Atia on 2019-09-03
 */

data class ContactsModel(
    val message: String,
    val data: List<Data>) {

    data class Data(
        val id: String,
        val firstName: String,
        val lastName: String,
        val age: Int,
        val photo: String
    )
}