package mazen.abdelgawad.news.common.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


sealed class NetworkState {
    data object Connected : NetworkState()
    data object Disconnected : NetworkState()
}

class NetworkStateMonitor(private val applicationContext: Context) {

    private val networkMutableStateFlow: MutableStateFlow<NetworkState> =
        MutableStateFlow(getNetworkState())

    private val connectivityManager by lazy {
        applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    fun observeNetworkState(): StateFlow<NetworkState> {
        val networkCallback: ConnectivityManager.NetworkCallback =
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    networkMutableStateFlow.value = NetworkState.Connected
                }

                override fun onLost(network: Network) {
                    networkMutableStateFlow.value = NetworkState.Disconnected
                }
            }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
        } else {
            val builder = NetworkRequest.Builder()
            connectivityManager.registerNetworkCallback(builder.build(), networkCallback)
        }

        return networkMutableStateFlow.asStateFlow()
    }

    fun getNetworkState(): NetworkState {
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo

        return if (activeNetwork != null && activeNetwork.isConnected) {
            NetworkState.Connected
        } else {
            NetworkState.Disconnected
        }
    }
}
