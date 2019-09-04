package com.ut.contact.utils.network

import com.google.gson.Gson
import com.ut.contact.entity.ErrorEntity
import com.ut.contact.model.ErrorModel
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by Atia on 2019-09-04
 */

class IoException(private val errorMessage: String?) : IOException(errorMessage) {

    companion object {
        fun fetchErrorMessage(e: HttpException): String {
            var errorMessage = "${e.message}"
            var errorModel: ErrorModel? = null

            val response: String = e.response().errorBody()?.bytes()?.let {
                String(it)
            } ?: ""

            errorModel = Gson().fromJson(response, ErrorEntity::class.java).toError()

            errorModel.message?.let {
                errorMessage = it
            }

            return errorMessage
        }
    }

    fun fetchErrorMessage(): String {
        return errorMessage ?: localizedMessage
    }
}