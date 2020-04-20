package com.prabha.wekan.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.prabha.wekan.base.BaseViewModel
import com.prabha.wekan.network.NetworkCallback
import com.prabha.wekan.ui.model.FuelStationResponse
import com.prabha.wekan.ui.model.FuelStationsModel
import com.prabha.wekan.ui.repository.FuelStationRepository
import javax.inject.Inject


class FuelStationViewModel @Inject constructor(val repository : FuelStationRepository) :  BaseViewModel(){

    val fuelStationDataLiveData = MutableLiveData<ArrayList<FuelStationsModel>>()
    val fuelStationErrorDataLiveData = MutableLiveData<String>()
    val fuelStationNoDataFound = MutableLiveData<String>()
    fun observeFuelStationResponse() = fuelStationDataLiveData
    fun observeFuelStationNoDataFound() = fuelStationNoDataFound
    fun observeFuelStationErrorResponse() = fuelStationErrorDataLiveData

    init {
        repository.getFuelStationlist(object : NetworkCallback {
            override fun onSuccess(response: Any?) {
                val responseData = response as FuelStationResponse
                if (responseData.FuelStations?.size!!.compareTo(0) > 0 ) {
                    fuelStationDataLiveData.postValue(responseData.FuelStations)
                }else{
                    fuelStationNoDataFound.postValue("No fuel stations found")
                }
            }
            override fun onError(error: Throwable?) {
                fuelStationErrorDataLiveData.postValue(error!!.message)
            }
        })
    }

}
