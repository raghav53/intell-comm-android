package com.intell.comm.base.di

import android.content.Context
import android.content.SharedPreferences
import com.intell.comm.localDatabase.sharePreferenace.SharedPref
import com.intell.comm.localDatabase.sharePreferenace.SharedPrefImpl
import com.intell.comm.network.ApiService
import com.intell.comm.network.NetworkAvailability
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.intell.comm.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * each time your app requests the binding, Hilt creates a new instance of the needed type.
 * this claa use for create a Singleton Component of each object and Inject in any class in project
 * */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * provide a function for base url
     * */
    @Provides
    fun providesUrl() = BuildConfig.BASE_URL

    /**
     *
     * @param url is find by [providesUrl] function.
     * [providesApiService] is a Singleton object and it's use in API handling.
     * */

    @Provides
    @Singleton
    fun providesApiService(url: String): ApiService {
        val client = OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)

/* this is use for static header*/
        client.addInterceptor { chain ->
            var request = chain.request()
            request = request
                .newBuilder()
//                .addHeader("authorization", "Bearer xxx")
                .build()
            chain.proceed(request)
        }

        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
            .create(ApiService::class.java)

    }


    /**
     *
     * create a Singleton object of [SharedPreferences]
     * */
    @Singleton
    @Provides
    fun sharedPreferences(@ApplicationContext context: Context): SharedPreferences? {
        return context.getSharedPreferences(
            BuildConfig.APPLICATION_ID,
            Context.MODE_PRIVATE
        )
    }

    /**
     *
     * @param sharedPreferences is created in above function sharedPreferences
     * create a Singleton object of [SharedPref] and it's use store a small data
     * */

    @Singleton
    @Provides
    fun sharedPref(sharedPreferences: SharedPreferences?): SharedPref {
        return SharedPrefImpl(sharedPreferences!!)
    }

    @Singleton
    @Provides
    fun networkAvailability(@ApplicationContext context: Context): NetworkAvailability {
        return NetworkAvailability(context)
    }

}