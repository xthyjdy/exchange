package vch.proj.exchange.api

import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query
import vch.com.exchange.model.NBUModel
import vch.com.exchange.model.PBModel

interface NBUApi {
    @GET("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?&json")
    suspend fun getAll(
            @Query("date") date: String
    ) : Response<List<NBUModel>>
}