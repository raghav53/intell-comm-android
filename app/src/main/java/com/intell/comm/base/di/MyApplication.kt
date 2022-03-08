package com.intell.comm.base.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.appcompat.app.AppCompatDelegate
import androidx.work.*
import com.intell.comm.base.repository.MainApiRepository
import com.intell.comm.localDatabase.AppDatabase
import com.intell.comm.localDatabase.sharePreferenace.SharedPref
import com.intell.comm.utils.NoInternetConnection
import com.intell.comm.workManger.UploadPendingPostOnServer
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApplication : Application() {

    @Inject
    lateinit var mainApiRepository: MainApiRepository

    @Inject
    lateinit var sharedPref: SharedPref

    var isInternet: Boolean = true
    var noInternetConnection: NoInternetConnection? = null

    var myApplication: MyApplication? = null

    override fun onCreate() {
        super.onCreate()
        myApplication = this
        // Disable Dark Mode.
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        networkStateChange(this)
//        postRentDetectUploadingStatus()
    }

    private fun postRentDetectUploadingStatus() {
        AppDatabase.getInstance(this).postDao()
            .getStatusPostRentModelLive(false)
            .observeForever {
                if (it != null) {
                    //when insert a post data in database then it update on server
                    uploadPendingJobsToTheServer()
                }
            }
    }

    private fun uploadPendingJobsToTheServer() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val uploadWorkRequest: WorkRequest =
            OneTimeWorkRequestBuilder<UploadPendingPostOnServer>()
                .setInputData(workDataOf("VENDOR_ID_KEY" to "vendorId"))
                .setConstraints(constraints)
                .build()

        WorkManager
            .getInstance(this)
            .enqueue(uploadWorkRequest)
    }


    private fun networkStateChange(context: Context) {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val builder = NetworkRequest.Builder()
        builder.addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        builder.addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        builder.addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        val callback: ConnectivityManager.NetworkCallback =
            object : ConnectivityManager.NetworkCallback() {
                override fun onLost(network: Network) {
                    super.onLost(network)
                    isInternet = false
                    if (noInternetConnection != null)
                        noInternetConnection!!.internetAvailableStatus(false)
                }

                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    isInternet = true
                    if (noInternetConnection != null)
                        noInternetConnection!!.internetAvailableStatus(true)
                }
            }
        cm!!.registerNetworkCallback(builder.build(), callback)
    }


}
