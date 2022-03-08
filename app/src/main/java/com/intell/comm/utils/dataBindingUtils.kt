package com.intell.comm.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.intell.comm.R
import com.bumptech.glide.Glide
import com.intell.comm.BuildConfig
import com.intell.comm.base.event.EditTextValueChangeEvent


////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////// ImageVIew Open /////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
@BindingAdapter(value = ["setNormalImage"])
fun setNormalImage(imageView: ImageView, image_url: String?) {
    if (image_url != null) {
        if (image_url.isEmpty()) {
            imageView.setImageResource(R.drawable.ic_image_placeholder)
        } else {
            Glide.with(imageView.context).load(BuildConfig.MEDIA_URL + image_url)
                .placeholder(R.drawable.ic_image_placeholder).into(imageView)
        }
    } else {
        imageView.setImageResource(R.drawable.ic_image_placeholder)
    }
}

@BindingAdapter(value = ["setProfileImage"])
fun setProfileImage(imageView: ImageView, image_url: String?) {
    if (image_url != null) {
        if (image_url.isEmpty()) {
            imageView.setImageResource(R.drawable.ic_image_placeholder)
        } else {
            Glide.with(imageView.context).load(BuildConfig.MEDIA_URL + image_url)
                .placeholder(R.drawable.ic_profile_placeholder).into(imageView)
        }
    } else {
        imageView.setImageResource(R.drawable.ic_profile_placeholder)
    }
}

@BindingAdapter(value = ["setDrawableImage"])
fun setDrawableImage(imageView: ImageView, drawable: Int) {
    if (drawable != -1) {
        imageView.setImageResource(drawable)
    }
}

@BindingAdapter(value = ["setStringText"])
fun setStringText(view: View, string: Int) {
    if (string != -1) {
        if (view is TextView)
            view.text = view.context.resources.getString(string)
        else if (view is EditText)
            view.setText(view.context.resources.getString(string))
    }
}

///////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////// ImageView Close /////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////// EditText Open /////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////

@BindingAdapter(value = ["editTextChange"])
fun editTextChange(editText: EditText, editTextValue: EditTextValueChangeEvent) {
    editText.addTextChangedListener(object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            // TODO Auto-generated method stub
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            // TODO Auto-generated method stub
        }

        override fun afterTextChanged(s: Editable) {
            editTextValue.value = editText
        }
    })
    if (editText.text.toString().trim().isEmpty()) {
        editText.background =
            ContextCompat.getDrawable(editText.context, R.drawable.edit_text_selected_red)
    }
}

@BindingAdapter(value = ["editTextChangeNoBg"])
fun editTextChangeNoBg(editText: EditText, editTextValue: EditTextValueChangeEvent) {
    editText.addTextChangedListener(object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            // TODO Auto-generated method stub
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            // TODO Auto-generated method stub
        }

        override fun afterTextChanged(s: Editable) {
            editTextValue.value = editText
        }
    })
}

@BindingAdapter("android:edit_drawable")
fun setEditDrawable(editText: EditText, drawable: Int) {
    if (drawable != -1) {
        editText.background =
            ContextCompat.getDrawable(editText.context, drawable)
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////// EditText Close ////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////