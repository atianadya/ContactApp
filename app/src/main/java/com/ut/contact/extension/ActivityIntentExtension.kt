package com.ut.contact.extension

import android.app.Activity
import android.content.Intent

/**
 * Created by Atia on 2019-09-04
 */

fun Activity.start(target: Class<*>, requestCode: Int? = null, func: (Intent.() -> Unit)? = null) {
    val intent = Intent(this, target)
    func?.let { intent.it() }
    if (requestCode != null) {
        startActivityForResult(intent, requestCode)
    } else {
        startActivity(intent)
    }
}