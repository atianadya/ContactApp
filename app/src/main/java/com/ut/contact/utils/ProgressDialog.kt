package com.ut.contact.utils

import android.app.Dialog
import android.content.Context
import com.ut.contact.R

/**
 * Created by Atia on 2019-09-04
 */

class ProgressDialog(context: Context) : Dialog(context) {
    companion object {
        fun buildProgressDialog(context: Context) : ProgressDialog {
            return ProgressDialog(context, R.layout.loading_progress_dialog)
        }
    }

    constructor(context: Context, layoutResId: Int, isCancelable: Boolean = false) : this(context) {
        setupDefaultView(layoutResId, isCancelable)
    }

    private fun setupDefaultView(layoutResId: Int, isCancelable: Boolean) {
        setContentView(layoutResId)
        setCancelable(isCancelable)
        setCanceledOnTouchOutside(isCancelable)
    }
}