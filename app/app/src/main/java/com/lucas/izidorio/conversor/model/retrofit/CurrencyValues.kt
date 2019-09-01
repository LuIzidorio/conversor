package com.lucas.izidorio.conversor.model.retrofit

import com.google.gson.annotations.SerializedName

data class CurrencyValues(
    @SerializedName("CAD")
    val CAD: Double?,

    @SerializedName("HKD")
    val HKD: Double?,

    @SerializedName("ISK")
    val ISK: Double?,

    @SerializedName("PHP")
    val PHP: Double?,

    @SerializedName("DKK")
    val DKK: Double?,

    @SerializedName("HUF")
    val HUF: Double?,

    @SerializedName("CZK")
    val CZK: Double?,

    @SerializedName("AUD")
    val AUD: Double?,

    @SerializedName("RON")
    val RON: Double?,

    @SerializedName("SEK")
    val SEK: Double?,

    @SerializedName("IDR")
    val IDR: Double?,

    @SerializedName("INR")
    val INR: Double?,

    @SerializedName("BRL")
    val BRL: Double?,

    @SerializedName("RUB")
    val RUB: Double?,

    @SerializedName("HRK")
    val HRK: Double?,

    @SerializedName("JPY")
    val JPY: Double?,

    @SerializedName("THB")
    val THB: Double?,

    @SerializedName("CHF")
    val CHF: Double?,

    @SerializedName("SGD")
    val SGD: Double?,

    @SerializedName("PLN")
    val PLN: Double?,

    @SerializedName("BGN")
    val BGN: Double?,

    @SerializedName("TRY")
    val TRY: Double?,

    @SerializedName("CNY")
    val CNY: Double?,

    @SerializedName("NOK")
    val NOK: Double?,

    @SerializedName("NZD")
    val NZD: Double?,

    @SerializedName("ZAR")
    val ZAR: Double?,

    @SerializedName("USD")
    val USD: Double?,

    @SerializedName("MXN")
    val MXN: Double?,

    @SerializedName("ILS")
    val ILS: Double?,

    @SerializedName("GBP")
    val GBP: Double?,

    @SerializedName("KRW")
    val KRW: Double?,

    @SerializedName("MYR")
    val MYR: Double?
) {
    @SerializedName("oldValue")
    var oldCurrencyValue: Double = 0.0
    @SerializedName("newValue")
    var newCurrencyValue: Double = 0.0

    fun getCurrencyValue(currency: String): Double? {
        return when (currency) {
            "CAD" -> CAD
            "HKD" -> HKD
            "ISK" -> ISK
            "PHP" -> PHP
            "DKK" -> DKK
            "HUF" -> HUF
            "CZK" -> CZK
            "AUD" -> AUD
            "RON" -> RON
            "SEK" -> SEK
            "IDR" -> IDR
            "INR" -> INR
            "BRL" -> BRL
            "RUB" -> RUB
            "HRK" -> HRK
            "JPY" -> JPY
            "THB" -> THB
            "CHF" -> CHF
            "SGD" -> SGD
            "PLN" -> PLN
            "BGN" -> BGN
            "TRY" -> TRY
            "CNY" -> CNY
            "NOK" -> NOK
            "NZD" -> NZD
            "ZAR" -> ZAR
            "USD" -> USD
            "MXN" -> MXN
            "ILS" -> ILS
            "GBP" -> GBP
            "KRW" -> KRW
            "MYR" -> MYR
            else -> null
        }
    }
}