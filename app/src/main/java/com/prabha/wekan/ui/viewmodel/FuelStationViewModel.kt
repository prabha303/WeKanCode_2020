package com.prabha.wekan.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.prabha.wekan.base.BaseViewModel
import com.prabha.wekan.network.NetworkCallback
import com.prabha.wekan.ui.model.FuelStationResponse
import com.prabha.wekan.ui.repository.FuelStationRepository
import javax.inject.Inject


class FuelStationViewModel @Inject constructor(val repository : FuelStationRepository) :  BaseViewModel(){

    val fuelStationDataLiveData = MutableLiveData<FuelStationResponse>()
    val fuelStationErrorDataLiveData = MutableLiveData<String>()
    fun observeFuelStationResponse() = fuelStationDataLiveData
    fun observeFuelStationErrorResponse() = fuelStationErrorDataLiveData

    fun getFuelData() {

        repository.getFuelStationlist(object : NetworkCallback {
            override fun onSuccess(response: Any?) {
                val responseData = response as FuelStationResponse
                fuelStationDataLiveData.postValue(responseData)
            }
            override fun onError(error: Throwable?) {
                fuelStationErrorDataLiveData.postValue(error!!.message)
            }
        })
    }

}
