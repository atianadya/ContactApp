package com.ut.contact.extension

import com.ut.contact.utils.network.IoException
import retrofit2.HttpException

/**
 * Created by Atia on 2019-09-04
 */

fun Throwable.getErrorMessage(): String {
    return when (this) {
        is IoException -> fetchErrorMessage()
        is HttpException -> IoException.fetchErrorMessage(this)
        else -> "Something's wrong. Please try again."
    }
}