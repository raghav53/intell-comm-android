package com.intell.comm.network

import com.intell.comm.localDatabase.post.PostModel
import com.intell.comm.localDatabase.sharePreferenace.SharedPref
import com.intell.comm.localDatbase.language.LanguageResponse
import com.intell.comm.model.*

import javax.inject.Inject

/**
 * this class use for handle API in background threads
 * @param apiService is a singleton parameter which is an Interface for provide response of API
 * */
class ApiServiceImpl @Inject constructor(
    private val apiService: ApiService,
    private val sharedPref: SharedPref
) {

    suspend fun getLanguageData(): LanguageResponse =
        apiService.getLanguageData(languageLink)

    suspend fun simpleApiResponsePost(url: String, map: Map<String, String>): SimpleApiResponse =
        apiService.simpleApiResponsePost(url, map)

    suspend fun registerLoginAccount(
        isLogin: Boolean,
        map: HashMap<String, String>
    ): ApiResponse<RegisterLoginModel> {
        val response = if (isLogin) {
            apiService.loginAccount(loginRegisterCheckMobileUpdateEditProfileLink, map)
        } else {
            apiService.registerAccount(loginRegisterCheckMobileUpdateEditProfileLink, map)
        }
        sharedPref.putUserData(response.body)
        return response
    }

    suspend fun getAllRentAndPostRent(
        isGet: Boolean,
        map: HashMap<String, String>
    ): ApiResponse<List<PostModel>> {
        val response = if (isGet) {
            apiService.getAllPostRent(getAllRentAndPostRentLink, map)
        } else {
            apiService.post(getAllRentAndPostRentLink, map)
        }
        return response
    }


}