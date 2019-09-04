package com.ut.contact.common.base

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Atia on 2019-09-03
 */

abstract class BindableListItemViewHolder<out B : ViewDataBinding>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding: B = DataBindingUtil.bind(itemView)!!
}