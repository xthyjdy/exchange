package vch.proj.exchange.api

import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Query
import vch.com.exchange.model.PBModel

/**
 * NBU Api - interfase which allow connect to private bank api
 */
interface PBApi {
    /**
     * Get By Date - method which get data by date from private bank api
     * @param date - String
     * @return Response<PBModel>
     */
    @GET("https://api.privatbank.ua/p24api/exchange_rates?json&")
    suspend fun getByDate(
            @Query("date") date: String
    ) : Response<PBModel>
}