package com.ut.contact.main.addcontact

import android.app.Activity
import com.ut.contact.extension.start
import com.ut.contact.main.front.FrontWireframe

/**
 * Created by Atia on 2019-09-04
 */

class AddContactWireframe {
    companion object {
        fun startAddContact(source: Activity) {
            source.start(AddContactActivity::class.java)
        }
    }

    fun backToHome(source: Activity) {
        FrontWireframe.startMainActivityClearTop(source)
    }
}