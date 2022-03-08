package com.intell.comm.localDatbase.language

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * [LanguageResponse] this class only handle language list
 * and [LanguageModel] store all language in database
 * */

class LanguageResponse {
    @SerializedName("languages")
    @Expose
    private var languages: List<LanguageModel>? = null

    fun getLanguages(): List<LanguageModel>? {
        return languages
    }

    fun setLanguages(languages: List<LanguageModel>) {
        this.languages = languages
    }
}