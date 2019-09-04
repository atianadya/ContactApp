package com.ut.contact.entity

import com.ut.contact.model.BaseModel
import com.google.gson.annotations.SerializedName

/**
 * Created by Atia on 2019-09-03
 */

data class BaseEntity(@SerializedName("message") val message: String) {
    fun create() = BaseModel(message)
}