package com.prabha.wekan.ui.model

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
class FuelStationResponse: Serializable {

    @SerializedName("station_locator_url")
    @Expose
    var StationLocatorURL: String? = null

    @SerializedName("total_results")
    @Expose
    var TotalResults: Int? = null

    @SerializedName("station_counts")
    @Expose
    var StationCounts: StationCounts? = null


    @SerializedName("fuel_stations")
    @Expose
    var FuelStations : ArrayList<FuelStationsModel>? = ArrayList()

}











