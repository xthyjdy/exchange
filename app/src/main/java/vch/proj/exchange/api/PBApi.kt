package vch.proj.exchange.api

import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Query
import vch.com.exchange.model.PBModel

interface PBApi {
    @GET("/p24api/pubinfo?exchange&json&coursid=11")
    suspend fun getCurrent() : Response<List<PBModel>>

    @GET("https://api.privatbank.ua/p24api/exchange_rates?json&")
    suspend fun getAll(
            @Query("date") date: String
    ) : Response<PBModel>
}