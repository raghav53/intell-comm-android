package com.intell.comm.network

import com.intell.comm.localDatabase.post.PostModel
import com.intell.comm.localDatbase.language.LanguageResponse
import com.intell.comm.model.*
import retrofit2.http.*

interface ApiService {

    @GET
    suspend fun getLanguageData(@Url url: String): LanguageResponse

    @POST
    @FormUrlEncoded
    suspend fun simpleApiResponsePost(
        @Url url: String,
        @FieldMap map: Map<String, String>
    ): SimpleApiResponse

    @POST
    @FormUrlEncoded
    suspend fun registerAccount(
        @Url url: String,
        @FieldMap map: HashMap<String, String>
    ): ApiResponse<RegisterLoginModel>

    @GET
    suspend fun loginAccount(
        @Url url: String,
        @QueryMap map: HashMap<String, String>
    ): ApiResponse<RegisterLoginModel>

    @POST
    @FormUrlEncoded
    suspend fun post(
        @Url url: String,
        @FieldMap map: HashMap<String, String>
    ): ApiResponse<List<PostModel>>

    @GET
    suspend fun getAllPostRent(
        @Url url: String,
        @QueryMap map: HashMap<String, String>
    ): ApiResponse<List<PostModel>>

}