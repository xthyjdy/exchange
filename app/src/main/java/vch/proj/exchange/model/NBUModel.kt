package vch.com.exchange.model

import kotlinx.android.parcel.Parcelize
import vch.proj.exchange.model.ExchangeModel

@Parcelize
data class NBUModel(
        val r030: Int,
        val txt: String,
        val rate: Double,
        val cc: String,
        val exchangedate: String
) : ExchangeModel() {}