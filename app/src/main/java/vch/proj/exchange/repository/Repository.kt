package vch.com.exchange.repository

import retrofit2.Response
import vch.com.exchange.api.RetrofitInstance
import vch.com.exchange.model.NBUModel
import vch.com.exchange.model.PBModel
import vch.proj.exchange.util.Helper.l

class Repository {
    /**
     * PB Get By Date - method which get data by date
     * @param date - String
     * @return Response<PBModel>
     */
    suspend fun pbGetByDate(date: String): Response<PBModel> {
        return RetrofitInstance.pbApi.getByDate(date)
    }

    /**
     * NBU Get By Date - method which get data by date
     * @param date - String
     * @return Response<PBModel>
     */
    suspend fun nbuGetByDate(date: String): Response<List<NBUModel>> {
        return RetrofitInstance.nbuApi.getByDate(date)
    }
}