package com.example.demopro


import android.content.Context
import com.work.sploot.api.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiProduction {

    companion object{
        val BASE_URL:String ="http://13.235.241.15:3510/"
//            val BASE_URL:String ="http://3.89.70.210:3500/"


       // 13.235.241.15:3510
    //    val BASE_URL:String ="http://192.168.2.217:3000/"
            //"http://3.89.70.210:3500/"// "http://192.168.4.194:3000/"


       // val BASE_URL_IMAGE:String = BASE_URL+"images/"
    }

    private lateinit var mContext: Context

    constructor(context: Context) {
        this.mContext = context
    }


    private fun provideRestAdapter(): Retrofit {

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                //.client(OkHttpProduction.getOkHttpClient(mContext, true))
                 .client(getUnsafeOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    fun <S> provideService(serviceClass: Class<S>): S {
        return provideRestAdapter().create(serviceClass)
    }


    fun getUnsafeOkHttpClient(): OkHttpClient {

        PrefDelegate.init(mContext)

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        var accessToken by stringPref("access_token", null)

        var authInterceptor = AuthInterceptor()
        if(accessToken == null)
              authInterceptor.Token("")
        else
              authInterceptor.Token(accessToken!!)

        var client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(interceptor)
          /*  .readTimeout(45, TimeUnit.SECONDS)
            .writeTimeout(45,TimeUnit.SECONDS)*/
            .build()
        return client

    }

}