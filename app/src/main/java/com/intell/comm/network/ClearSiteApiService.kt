package com.intell.comm.network

import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */

/*private var httpLoggingInterceptor: HttpLoggingInterceptor? = HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)
private var httpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()*/

val client: OkHttpClient.Builder = OkHttpClient.Builder()
    .connectTimeout(120, TimeUnit.SECONDS)
    .readTimeout(120, TimeUnit.SECONDS)
    .writeTimeout(120, TimeUnit.SECONDS)

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl("baseUrl")
    .client(client.build())
    .build()

interface ClearSiteApiService {
    @GET("building/v2")
    suspend fun getBuildings(@Header("authorization") sessionToken: String,
                             @Query("serviceProviderId") serviceProviderId: String): String

    @Multipart
    @POST("photo/upload")
    fun uploadPhotos(@Part("vendorId") vendorId: String,
                     @Part filePart: MultipartBody.Part): Call<String?>

    @Multipart
    @POST("photo/upload")
    fun uploadPhotos(
        @Part("vendorId") vendorId: String,
        @Part("tzOffset") tzOffset: String,
        @Part("submitAt") submitAt: String,
        @Part("note") note: String,
        @Part filePart: Array<MultipartBody.Part?>
    ): Call<String?>

}