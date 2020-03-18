package com.prabha.wekan.ui.model

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
class Fuel: Serializable {
    @SerializedName("BD")
    @Expose
    var BD: FuelTotalForSub? = null

    @SerializedName("E85")
    @Expose
    var E85: FuelTotalForSub? = null

    @SerializedName("ELEC")
    @Expose
    var ELEC: FuelTotalForSub? = null

    @SerializedName("HY")
    @Expose
    var HY: FuelTotalForSub? = null

    @SerializedName("LNG")
    @Expose
    var LNG: FuelTotalForSub? = null

    @SerializedName("CNG")
    @Expose
    var CNG: FuelTotalForSub? = null

    @SerializedName("LPG")
    @Expose
    var LPG: FuelTotalForSub? = null
}