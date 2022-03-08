package com.intell.comm.base

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.intell.comm.R

/**
 * this class use for show toast message on screen
 * */
class MessageUtils {

    @ColorInt
    private val ERROR_COLOR = Color.parseColor("#D50000")

    @ColorInt
    private val INFO_COLOR = Color.parseColor("#08aad2")

    @ColorInt
    private val SUCCESS_COLOR = Color.parseColor("#388E3C")

    @ColorInt
    private val WARNING_COLOR = Color.parseColor("#FFA900")

    @ColorInt
    private val NORMAL_COLOR = Color.parseColor("#353A3E")

    /**
     * normal toast message show with
     * @see NORMAL_COLOR default color
     * */
    fun normal(view: View, message: String?) {
        if (message != null) {
            showCustomSnackBar(
                view,
                message,
                NORMAL_COLOR,
                false,
                ContextCompat.getDrawable(
                    view.context,
                    R.drawable.ic_baseline_check_circle_outline_24
                )!!
            )

        }
    }

    /**
     * success toast message show with green background color
     * @see SUCCESS_COLOR default color
     * */
    fun success(view: View, message: String) {
        showCustomSnackBar(
            view,
            message,
            SUCCESS_COLOR,
            true,
            ContextCompat.getDrawable(
                view.context,
                R.drawable.ic_baseline_check_circle_outline_24
            )!!
        )
    }

    /**
     * warning toast message show with yellow background color
     * @see WARNING_COLOR default color
     * */
    fun warning(view: View, message: String) {
        showCustomSnackBar(
            view,
            message,
            WARNING_COLOR,
            true,
            ContextCompat.getDrawable(view.context, R.drawable.ic_baseline_warning_amber_24)!!
        )
    }

    /**
     * info toast message show with green background color
     * @see INFO_COLOR default color
     * */
    fun info(view: View, message: String) {
        showCustomSnackBar(
            view,
            message,
            INFO_COLOR,
            true,
            ContextCompat.getDrawable(view.context, R.drawable.ic_outline_info_24)!!
        )
    }

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * error toast message show with red background color
     * @see ERROR_COLOR default color
     * */
    fun error(view: View, message: String) {
        showCustomSnackBar(
            view,
            message,
            ERROR_COLOR,
            true,
            ContextCompat.getDrawable(view.context, R.drawable.ic_baseline_block_24)!!
        )
    }

    /**
     * @see snackBar bottom layout message, show with grey background color
     * @param color is default color grey
     * */
    fun snackBar(view: View, message: String, color: Int = Color.GRAY) {
        val sbView: Snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        sbView.view.setBackgroundColor(color)
        sbView.show()

    }

    /**
     * create custom toast layout
     * @see showCustomSnackBar
     * */
    private fun showCustomSnackBar(
        @NonNull view: View,
        @NonNull message: String,
        @ColorInt bgColor: Int,
        withIcon: Boolean,
        icon: Drawable,
    ) {


        // Create the Snackbar
        val snackBar = Snackbar.make(view, "", Snackbar.LENGTH_LONG)
        snackBar.view.setBackgroundColor(bgColor)
        val context = snackBar.context

        // Get the Snackbar's layout view
        val layout = snackBar.view as Snackbar.SnackbarLayout
        // Hide the text
        val textView =
            layout.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
        textView.visibility = View.INVISIBLE

        val customLayout: View =
            (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                R.layout.custom_toast,
                null
            )
        val imgIcon = customLayout.findViewById<ImageView>(R.id.toast_icon)
        val sbText = customLayout.findViewById<TextView>(R.id.toast_text)

        if (withIcon) {
            imgIcon.background = icon
        } else {
            imgIcon.visibility = View.GONE
        }

        sbText.text = message
        sbText.setTextColor(Color.parseColor("#FFFFFF"))

        //If the view is not covering the whole snackbar layout, add this line
        layout.setPadding(0, 0, 0, 0)

        // Add the view to the Snackbar's layout
        layout.addView(customLayout, 0)
        // Show the Snackbar
        snackBar.show()


    }

}