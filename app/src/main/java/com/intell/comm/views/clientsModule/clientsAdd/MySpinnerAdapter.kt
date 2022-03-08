package com.intell.comm.views.clientsModule.clientsAdd

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.intell.comm.R
import com.intell.comm.model.SpinnerModel

class MySpinnerAdapter(private var mContext: Context, var resource: Int, var spinnerList: List<SpinnerModel>) :
    ArrayAdapter<SpinnerModel?>(mContext, resource, spinnerList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(mContext)
                .inflate(resource, parent, false)
        }
        val rowItem: SpinnerModel? = getItem(position)
        val txtTitle = view?.findViewById<View>(R.id.tv_title) as TextView
        txtTitle.text = rowItem?.title
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(mContext)
                .inflate(R.layout.spinner_drop_down_layout, parent, false)
        }
        val rowItem: SpinnerModel? = getItem(position)
        val txtTitle = view?.findViewById<View>(R.id.tv_title) as TextView
        txtTitle.text = rowItem?.title
        val imageView = view.findViewById<View>(R.id.iv_check) as ImageView
        if (rowItem?.isSelected!!) {
            imageView.visibility = View.VISIBLE
        } else {
            imageView.visibility = View.INVISIBLE
        }
        return view
    }


}