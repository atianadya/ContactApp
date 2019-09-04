package com.ut.contact.entity

import com.ut.contact.model.ErrorModel

/**
 * Created by Atia on 2019-09-04
 */

data class ErrorEntity(val message: String?) {
    fun toError() : ErrorModel {
        return ErrorModel(message)
    }
}