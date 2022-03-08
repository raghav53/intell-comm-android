package com.intell.comm.model

import android.graphics.Bitmap

class MediaModel(
    var id: Int = 0,
    var path: String = "",
    var type: Int = 0,//0=no image, 1=local image, 2= url link
    var mimeType: String="",
    var imageId: String=""
){
    var bitmap: Bitmap?=null
}