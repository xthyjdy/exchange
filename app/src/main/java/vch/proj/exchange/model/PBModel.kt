package vch.com.exchange.model

import kotlinx.android.parcel.Parcelize
import vch.proj.exchange.model.ExchangeModel

@Parcelize
data class PBModel(
        val ccy: String,
        val base_ccy: String,
        val buy: Double,
        val sale: Double,
) : ExchangeModel() {}