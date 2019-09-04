package com.ut.contact.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ut.contact.R

/**
 * Created by Atia on 2019-09-03
 */

@BindingAdapter("app:imageUrl")
fun loadImage(imageView: AppCompatImageView, url: String) {
    Glide.with(imageView.context)
        .load(url)
        .apply(RequestOptions()
            .placeholder(R.drawable.default_person)
            .centerCrop())
        .into(imageView)
}
