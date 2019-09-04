package com.ut.contact.main.detail

import android.app.Activity
import com.ut.contact.extension.start
import com.ut.contact.main.front.ContactCardItemViewModel
import com.ut.contact.main.front.FrontWireframe

/**
 * Created by Atia on 2019-09-04
 */

class DetailWireframe {
    companion object {
        const val INTENT_ID = "Detail.INTENT_ID"
        const val INTENT_FIRST_NAME = "Detail.INTENT_FIRST_NAME"
        const val INTENT_LAST_NAME = "Detail.INTENT_LAST_NAME"
        const val INTENT_AGE = "Detail.INTENT_AGE"
        const val INTENT_PHOTO_URL = "Detail.INTENT_PHOTO_URL"

        fun startDetail(source: Activity, data: ContactCardItemViewModel) {
            source.start(DetailActivity::class.java) {
                putExtra(INTENT_ID, data.id)
                putExtra(INTENT_FIRST_NAME, data.firstName)
                putExtra(INTENT_LAST_NAME, data.lastName)
                putExtra(INTENT_AGE, data.age)
                putExtra(INTENT_PHOTO_URL, data.photo)
            }
        }
    }

    fun backToHome(source: Activity) {
        FrontWireframe.startMainActivityClearTop(source)
    }
}