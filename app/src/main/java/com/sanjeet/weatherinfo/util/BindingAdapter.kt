package com.sanjeet.weatherinfo.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sanjeet.weatherinfo.R

@BindingAdapter("urlToImage")
fun urlToImage(view: ImageView, imageUrl: String?) {

    if (!imageUrl.isNullOrBlank()) {
        Glide.with(view.context).load(imageUrl).apply(
            RequestOptions()
                .placeholder(R.drawable.cloud1)
        ).into(view)
    }
}