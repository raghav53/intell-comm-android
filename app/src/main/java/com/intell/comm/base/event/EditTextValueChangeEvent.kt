/*
 *  Copyright 2017 Google Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.intell.comm.base.event

import android.widget.EditText
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import java.lang.Exception

/**
 * A SingleLiveEvent used for messages. Like a [SingleLiveEvent] but also prevents
 * null messages and uses a custom observer.
 *
 *
 * Note that only one observer is going to be notified of changes.
 */
class EditTextValueChangeEvent : SingleLiveEvent<EditText>() {
    interface EditTextObserver {
        fun onEditTextReceived(editText: EditText)
    }

    fun observe(owner: LifecycleOwner?, observer: EditTextObserver) {
        super.observe(owner!!, Observer { et: EditText? ->
            try {
                if (et == null) {
                    return@Observer
                }
                observer.onEditTextReceived(et)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
    }
}