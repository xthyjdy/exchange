package vch.com.exchange.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vch.com.exchange.util.Constant.Companion.NBU_URL
import vch.com.exchange.util.Constant.Companion.PB_URL
import vch.proj.exchange.api.NBUApi
import vch.proj.exchange.api.PBApi

object RetrofitInstance {
    private val pbRetrofit by lazy {
        Retrofit.Builder()
                .baseUrl(PB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    val pbApi: PBApi by lazy {
        pbRetrofit.create(PBApi::class.java)
    }

    private val nbuRetrofit by lazy {
        Retrofit.Builder()
                .baseUrl(NBU_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    val nbuApi: NBUApi by lazy {
        nbuRetrofit.create(NBUApi::class.java)
    }
}