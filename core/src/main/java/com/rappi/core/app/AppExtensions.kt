package com.rappi.core.app

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities


fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetwork ?: return false
    val capability = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
    return when {
        capability.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        capability.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        capability.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        capability.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
        else -> false
    }
}
