package com.prabha.wekan.ui.repository

import com.prabha.wekan.base.BaseRepository
import com.prabha.wekan.network.NetworkCallback
import javax.inject.Inject


 private  const val APIKEY = "DEMO_KEY"
 private  const val FUELTYPE = "E85,ELEC"
 private  const val STATE = "CA"


class FuelStationRepository @Inject constructor(var fuelStationApiService: FuelStationApiService): BaseRepository() {


    fun driverOffline(limit : Int, networkCallback: NetworkCallback) {

        makeNetworkCall(fuelStationApiService.getFuelDetail(APIKEY,FUELTYPE, STATE, limit),networkCallback)

    }


}