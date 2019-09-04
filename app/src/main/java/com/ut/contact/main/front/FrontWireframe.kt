package com.ut.contact.main.front

import android.app.Activity
import android.content.Intent
import com.ut.contact.extension.start
import com.ut.contact.main.addcontact.AddContactWireframe
import com.ut.contact.main.detail.DetailWireframe

/**
 * Created by Atia on 2019-09-03
 */

class FrontWireframe {
    companion object {
        fun startMainActivityClearTop(source: Activity) {
            source.start(FrontActivity::class.java) {
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        }
    }
    fun openDetail(source: Activity, data: ContactCardItemViewModel) {
        DetailWireframe.startDetail(source, data)
    }

    fun openAddContact(source: Activity) {
        AddContactWireframe.startAddContact(source)
    }
}