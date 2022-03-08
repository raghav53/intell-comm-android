package com.intell.comm.base.model

import android.graphics.Bitmap
import android.net.Uri

class BaseModel(
    var id: Int = -1,
    var string1: String = "",
    var string2: String = "",
    var string3: String = "",
    var string4: String = "",
    var string5: String = "",
    var string6: String = "",
    var string7: String = "",
    var string8: String = "",
    var int1: Int = -1,
    var int2: Int = -1,
    var type: Int = 0,
    var uri: Uri? = null,
    var bitmap: Bitmap? = null,
    val direction: androidx.navigation.NavDirections? = null
)