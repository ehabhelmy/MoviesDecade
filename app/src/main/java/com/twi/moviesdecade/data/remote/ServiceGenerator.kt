package com.twi.moviesdecade.data.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Ehab on 4/21/2018.
 */

class ServiceGenerator {

    companion object {
        private const val TIMEOUT_CONNECT = 60L   //In seconds
        private const val TIMEOUT_READ = 60L   //In seconds
        private const val CONTENT_TYPE = "Content-Type"
        private const val CONTENT_TYPE_JSON = "application/json"
    }

    private val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()

    private val headerInterceptor = { chain : Interceptor.Chain ->
        val original = chain.request()
        val request = original.newBuilder()
                .header(CONTENT_TYPE, CONTENT_TYPE_JSON)
                .method(original.method(), original.body())
                .build()

        chain.proceed(request)
    }

    init {
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpBuilder.addInterceptor(interceptor)
        okHttpBuilder.readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
        okHttpBuilder.connectTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS)
        okHttpBuilder.addInterceptor(headerInterceptor)
    }

    fun <s> createService(serviceClass: Class<s>, url: String): s {
        val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(serviceClass)
    }
}
