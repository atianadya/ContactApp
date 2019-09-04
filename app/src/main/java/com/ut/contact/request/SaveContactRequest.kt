package com.ut.contact.request

import com.google.gson.annotations.SerializedName

/**
 * Created by Atia on 2019-09-03
 */

data class SaveContactRequest(
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("age") val age: Int,
    @SerializedName("photo") val photo: String
)