package com.prabha.wekan.ui.repository

import com.prabha.wekan.ui.model.FuelStationResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface FuelStationApiService {

    @GET(EndpointsUtil.FUELURL)
    fun getFuelDetail(@Query("api_key") api_key: String,@Query("fuel_type") fuel_type: String,@Query("state") state: String,@Query("limit") limit: Int): Observable<FuelStationResponse>
}