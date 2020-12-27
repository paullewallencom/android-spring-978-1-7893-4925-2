package com.sunnat629.clientside.api

import com.google.gson.GsonBuilder
import com.sunnat629.clientside.util.Constants
import com.sunnat629.clientside.basicauth.BasicAuthInterceptor
import com.sunnat629.clientside.repository.CommentService
import com.sunnat629.clientside.repository.PostService
import com.sunnat629.clientside.repository.ProfileService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object APIClient{
    fun getRetrofitBuilder(username:String?, password:String?): Retrofit {
        return Retrofit.Builder()
            .client(getOkhttpClient(username!!, password!!))
            .baseUrl(Constants.API_BASE_PATH)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(gsonConverter())
            .build()
    }
    fun getRetrofitBuilder(): Retrofit {
        return Retrofit.Builder()
            .client(getOkhttpClient())
            .baseUrl(Constants.API_BASE_PATH)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(gsonConverter())
            .build()
    }

    fun gsonConverter(): GsonConverterFactory {
        return GsonConverterFactory
            .create(
                GsonBuilder()
                    .setLenient()
                    .disableHtmlEscaping()
                    .create()
            )
    }

    fun getOkhttpClient(profileName: String, password: String): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(BasicAuthInterceptor(profileName, password))
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    fun getOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    // get profile request builder
    fun profileAPICall(username:String?, password:String?) = getRetrofitBuilder(username, password)
        .create(ProfileService::class.java)

    // get profile request builder
    fun newProfileAPICall() = getRetrofitBuilder()
        .create(ProfileService::class.java)

    // get post request builder
    fun postAPICall(username:String, password:String) = getRetrofitBuilder(username, password)
        .create(PostService::class.java)

    // get comment request builder
    fun commentAPICall(username:String, password:String) = getRetrofitBuilder(username, password)
        .create(CommentService::class.java)
}