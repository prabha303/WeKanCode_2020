package com.prabha.wekan.ui.model

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
class FuelStationsModel: Serializable {

    @SerializedName("id")
    @Expose
    var ID: Int? = 0

    @SerializedName("access_code")
    @Expose
    var AccessCode: String? = null

    @SerializedName("access_days_time")
    @Expose
    var AccessDaysTime: String? = ""

    @SerializedName("access_detail_code")
    @Expose
    var AccessDetailCode: String? = null

    @SerializedName("cards_accepted")
    @Expose
    var CardsSccepted: String? = null

    @SerializedName("date_last_confirmed")
    @Expose
    var DateLastConfirmed: String? = ""

    @SerializedName("expected_date")
    @Expose
    var ExpectedDate: String? = null

    @SerializedName("fuel_type_code")
    @Expose
    var FuelTypeCode: String? = null

    @SerializedName("groups_with_access_code")
    @Expose
    var GroupsWithAccessCode: String? = null

    @SerializedName("open_date")
    @Expose
    var OpenDate: String? = null

    @SerializedName("owner_type_code")
    @Expose
    var OwnerTypeCode: String? = null

    @SerializedName("status_code")
    @Expose
    var StatusCode: String? = null

    @SerializedName("station_name")
    @Expose
    var StationName: String? = ""

    @SerializedName("station_phone")
    @Expose
    var StationPhone: String? = null

    @SerializedName("updated_at")
    @Expose
    var UpdatedAt: String? = null

    @SerializedName("facility_type")
    @Expose
    var FacilityType: String? = ""

    @SerializedName("latitude")
    @Expose
    var Latitude: Double = 0.0
    @SerializedName("longitude")
    @Expose
    var Longitude: Double = 0.0

    @SerializedName("city")
    @Expose
    var City: String? = null
    @SerializedName("intersection_directions")
    @Expose
    var IntersectionDirections: String? = ""

    @SerializedName("street_address")
    @Expose
    var StreetAddress: String? = ""

    @SerializedName("groups_with_access_code_fr")
    @Expose
    var GroupsWithAccessCodeFr: String? = null

    @SerializedName("country")
    @Expose
    var Country: String? = ""



}