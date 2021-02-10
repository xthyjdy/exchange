package vch.proj.exchange.api

import retrofit2.http.GET
import retrofit2.Response
import vch.com.exchange.model.PBModel

interface PBApi {
    @GET("/p24api/pubinfo?exchange&json&coursid=11")
    suspend fun getAll() : Response<List<PBModel>>
}