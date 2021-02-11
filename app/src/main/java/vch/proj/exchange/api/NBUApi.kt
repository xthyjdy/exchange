package vch.proj.exchange.api

import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query
import vch.com.exchange.model.NBUModel
import vch.com.exchange.model.PBModel

/**
 * NBU Api - interfase which allow connect to nbu api
 */
interface NBUApi {
    /**
     * Get By Date - method which get data by date from nbu api
     * @param date - String
     * @return Response<PBModel>
     */
    @GET("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?&json")
    suspend fun getByDate(
            @Query("date") date: String
    ) : Response<List<NBUModel>>
}