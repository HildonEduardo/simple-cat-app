package com.hdsw.asimpleapp.data.remote.network

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

object NetworkClient {

    private const val CACHE_SIZE = (5 * 1024 * 1024).toLong() // 5 MB
    private const val CACHE_MAX_AGE = 5 * 60 // 5 minutes

    fun create(context: Context): Retrofit {
        val cacheDir = File(context.cacheDir, "http-cache")
        val cache = Cache(cacheDir, CACHE_SIZE)

        val okHttpClient = OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor { chain ->
                var request = chain.request()

                request = if (isNetworkAvailable(context)) {
                    request.newBuilder().header("Cache-Control", "public, max-age=$CACHE_MAX_AGE")
                        .build()
                } else {
                    request.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=$CACHE_MAX_AGE")
                        .build()
                }

                chain.proceed(request)
            }
            .build()

        return Retrofit.Builder()
            .baseUrl("https://cataas.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
