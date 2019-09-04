package com.ut.contact.main.detail.detailedit

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import com.ut.contact.main.front.FrontWireframe

/**
 * Created by Atia on 2019-09-04
 */

class DetailEditWireframe {
    fun startMain(source: FragmentActivity) {
        FrontWireframe.startMainActivityClearTop(source)
    }
}