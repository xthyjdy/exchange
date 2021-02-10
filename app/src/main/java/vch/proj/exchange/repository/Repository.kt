package vch.com.exchange.repository

import retrofit2.Response
import vch.com.exchange.api.RetrofitInstance
import vch.com.exchange.model.NBUModel
import vch.com.exchange.model.PBModel

class Repository {
    //PRIVATE BANK
    suspend fun pbGetAll(): Response<List<PBModel>> {
        return RetrofitInstance.pbApi.getAll()
    }

    //NBU
    suspend fun nbuGetAll(date: String): Response<List<NBUModel>> {
        return RetrofitInstance.nbuApi.getAll(date)
    }
}